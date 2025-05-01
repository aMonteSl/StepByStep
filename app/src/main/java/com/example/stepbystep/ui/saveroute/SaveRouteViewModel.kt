package com.example.stepbystep.ui.saveroute

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.stepbystep.data.local.RouteRoomDatabase
import com.example.stepbystep.data.repository.RouteRepository
import com.example.stepbystep.domain.model.Route
import com.example.stepbystep.domain.model.Point
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * ViewModel para la pantalla de guardado de ruta.
 * 
 * Se encarga de:
 * - Almacenar temporalmente los datos de la ruta a guardar
 * - Validar el formulario de entrada
 * - Realizar la operación de guardado en la base de datos
 * - Gestionar la ruta de la imagen asociada
 * 
 * @param context Contexto necesario para acceder a la base de datos local
 */
class SaveRouteViewModel(context: Context) : ViewModel() {
    
    private val repository: RouteRepository
    
    /**
     * Inicialización del repositorio con acceso a la base de datos.
     */
    init {
        val database = RouteRoomDatabase.getInstance(context)
        repository = RouteRepository(database.routeDao())
    }

    // Datos de la ruta a guardar
    private val _distance = MutableLiveData<Double>()
    val distance: LiveData<Double> = _distance

    private val _duration = MutableLiveData<Long>()
    val duration: LiveData<Long> = _duration

    private val _elevationGain = MutableLiveData<Double>()
    val elevationGain: LiveData<Double> = _elevationGain

    private val _elevation = MutableLiveData<Double>()
    val elevation: LiveData<Double> = _elevation

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _imagePath = MutableLiveData<String?>()
    val imagePath: LiveData<String?> = _imagePath

    private val _altitudes = MutableLiveData<DoubleArray>()

    // Campos del formulario
    val routeName = MutableLiveData<String>()
    val routeDescription = MutableLiveData<String>()
    
    // Puntos de la ruta para el mapa
    private val _routePoints = MutableLiveData<List<LatLng>>()
    val routePoints: LiveData<List<LatLng>> = _routePoints

    /**
     * LiveData que indica si el formulario es válido para guardar.
     * Se actualiza automáticamente cuando cambian los campos del formulario.
     */
    val isFormValid = MediatorLiveData<Boolean>().apply {
        addSource(routeName) { validateForm() }
        addSource(routeDescription) { validateForm() }
    }

    /**
     * Establece los datos de la ruta recibidos de la actividad anterior.
     * 
     * @param distance Distancia total de la ruta en km
     * @param duration Duración total en milisegundos
     * @param elevationGain Ganancia de elevación total en metros
     * @param elevation Elevación máxima alcanzada en metros
     * @param points Lista de coordenadas que forman la ruta
     * @param altitudes Array con las altitudes de cada punto (opcional)
     * @param isImported Flag que indica si es una ruta importada de GPX
     */
    fun setRouteData(
        distance: Double,
        duration: Long,
        elevationGain: Double,
        elevation: Double,
        points: List<LatLng>,
        altitudes: DoubleArray? = null,
        isImported: Boolean = false
    ) {
        _distance.value = distance
        _duration.value = duration
        _elevationGain.value = elevationGain
        _elevation.value = elevation
        _routePoints.value = points
        _altitudes.value = altitudes
        
        // Establecer la fecha actual como predeterminada
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        _date.value = formatter.format(Date())
        
        // Solo establecer un nombre predeterminado si no es una ruta importada
        // o si el nombre actual está vacío
        if (!isImported && (routeName.value.isNullOrEmpty())) {
            val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
            routeName.value = "Ruta ${timeFormatter.format(Date())}"
        }
    }

    /**
     * Establece la ruta de la imagen asociada a la ruta.
     * 
     * @param path Ruta absoluta al archivo de imagen en el almacenamiento interno
     */
    fun setImagePath(path: String?) {
        _imagePath.value = path
    }

    /**
     * Valida que el formulario tenga todos los campos requeridos.
     * Actualiza el LiveData isFormValid según el resultado.
     */
    private fun validateForm() {
        val name = routeName.value
        val description = routeDescription.value
        isFormValid.value = !name.isNullOrBlank() && !description.isNullOrBlank()
    }

    /**
     * Guarda la ruta en la base de datos.
     * Convierte los datos temporales a un objeto Route y lo guarda
     * usando el repositorio.
     */
    fun saveRoute() {
        if (isFormValid.value != true) return
        
        val points = _routePoints.value ?: emptyList()
        val altitudes = _altitudes.value
        
        // Verificar el valor del path de la imagen antes de crear la ruta
        Log.d("SaveRoute", "imagePath before creating Route: ${imagePath.value}")
        
        val routePoints = if (altitudes != null && altitudes.size == points.size) {
            // Usar la altitud individual para cada punto
            points.mapIndexed { index, latLng -> 
                Point(
                    latitude = latLng.latitude,
                    longitude = latLng.longitude,
                    altitude = altitudes[index],
                    timestamp = 0L
                )
            }
        } else {
            // Alternativa si faltan datos de altitud
            points.map { 
                Point(
                    latitude = it.latitude,
                    longitude = it.longitude,
                    altitude = _elevation.value ?: 0.0,
                    timestamp = 0L
                )
            }
        }
        
        val route = Route(
            id = 0, // Será generado por Room
            name = routeName.value ?: "",
            description = routeDescription.value ?: "",
            date = date.value ?: "",
            distance = distance.value ?: 0.0,
            duration = duration.value ?: 0L,
            elevation = elevation.value ?: 0.0,
            elevationGain = elevationGain.value ?: 0.0,
            imagePath = imagePath.value,  // Puede ser null
            points = routePoints
        )
        
        // Registro para verificar que la ruta tiene el path de la imagen
        Log.d("SaveRoute", "Route created with imagePath: ${route.imagePath}")
        
        viewModelScope.launch {
            repository.addRoute(route)
        }
    }
}