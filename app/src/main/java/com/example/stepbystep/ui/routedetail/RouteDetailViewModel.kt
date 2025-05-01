package com.example.stepbystep.ui.routedetail

import android.content.Context
import androidx.lifecycle.*
import com.example.stepbystep.data.local.RouteRoomDatabase
import com.example.stepbystep.data.repository.RouteRepository
import com.example.stepbystep.domain.model.Route
import com.example.stepbystep.domain.model.Point
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * ViewModel para la pantalla de detalle de ruta.
 * 
 * Se encarga de:
 * - Cargar los datos de la ruta seleccionada
 * - Preparar los datos para el gráfico de elevación
 * - Exportar la ruta a formato GPX para compartir
 * 
 * @param context Contexto necesario para acceder a la base de datos y archivos
 */
class RouteDetailViewModel(private val context: Context) : ViewModel() {
    
    private val repository: RouteRepository
    
    /**
     * Inicialización del repositorio con acceso a la base de datos.
     */
    init {
        val database = RouteRoomDatabase.getInstance(context)
        repository = RouteRepository(database.routeDao())
    }

    // LiveData para la ruta completa
    private val _route = MutableLiveData<Route>()
    val route: LiveData<Route> = _route

    // LiveData para los datos del gráfico de elevación
    private val _chartData = MutableLiveData<List<Pair<Float, Float>>>()
    val chartData: LiveData<List<Pair<Float, Float>>> = _chartData

    /**
     * Carga la información de la ruta especificada desde el repositorio.
     * También prepara los datos para el gráfico de elevación.
     * 
     * @param routeId ID de la ruta a cargar
     */
    fun loadRoute(routeId: Long) {
        viewModelScope.launch {
            val loadedRoute = repository.getRouteById(routeId)
            _route.value = loadedRoute
            
            // Preparar datos para el gráfico
            prepareChartData(loadedRoute.points)
        }
    }

    /**
     * Prepara los datos para el gráfico de elevación.
     * Convierte la lista de puntos a pares (distancia, altitud) donde la distancia
     * es acumulativa desde el inicio de la ruta.
     * 
     * @param points Lista de puntos geográficos de la ruta
     */
    private fun prepareChartData(points: List<Point>) {
        if (points.isEmpty()) {
            _chartData.value = emptyList()
            return
        }

        // Crear puntos para el gráfico donde x es distancia (acumulada) e y es altitud
        val chartPoints = mutableListOf<Pair<Float, Float>>()
        var accumulatedDistance = 0f
        
        chartPoints.add(Pair(0f, points.first().altitude.toFloat()))
        
        for (i in 1 until points.size) {
            val current = points[i]
            val previous = points[i-1]
            
            // Calcular distancia entre puntos consecutivos
            val distance = calculateDistance(
                previous.latitude, previous.longitude,
                current.latitude, current.longitude
            )
            
            accumulatedDistance += distance
            chartPoints.add(Pair(accumulatedDistance, current.altitude.toFloat()))
        }
        
        _chartData.value = chartPoints
    }
    
    /**
     * Calcula la distancia entre dos coordenadas geográficas usando la fórmula haversine.
     * El resultado se expresa en metros.
     */
    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        // Fórmula de distancia haversine
        val earthRadius = 6371000.0 // metros
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return (earthRadius * c).toFloat()
    }

    /**
     * Exporta la ruta actual a un archivo GPX para compartir.
     * Crea un archivo temporal con toda la información de la ruta y sus puntos.
     * 
     * @return Archivo GPX generado
     * @throws IllegalStateException si no hay ruta cargada
     */
    suspend fun exportGpx(): File = withContext(Dispatchers.IO) {
        val route = route.value ?: throw IllegalStateException("No route loaded")
        
        // Crear un archivo temporal para los datos GPX
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val timestamp = dateFormat.format(Date())
        val filename = "route_${route.id}_$timestamp.gpx"
        val file = File(context.cacheDir, filename)
        
        // Generar contenido GPX
        FileWriter(file).use { writer ->
            writer.append("""
                <?xml version="1.0" encoding="UTF-8"?>
                <gpx version="1.1" creator="StepByStep App"
                  xmlns="http://www.topografix.com/GPX/1/1"
                  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xsi:schemaLocation="http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd">
                  <metadata>
                    <name>${route.name}</name>
                    <desc>${route.description}</desc>
                    <time>${route.date}</time>
                  </metadata>
                  <trk>
                    <name>${route.name}</name>
                    <desc>${route.description}</desc>
                    <trkseg>
            """.trimIndent())
            
            // Añadir puntos de la ruta
            for (point in route.points) {
                val timeStr = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                    .format(Date(point.timestamp))
                
                writer.append("""
                      <trkpt lat="${point.latitude}" lon="${point.longitude}">
                        <ele>${point.altitude}</ele>
                        <time>$timeStr</time>
                      </trkpt>
                """.trimIndent())
            }
            
            writer.append("""
                    </trkseg>
                  </trk>
                </gpx>
            """.trimIndent())
        }
        
        return@withContext file
    }
}