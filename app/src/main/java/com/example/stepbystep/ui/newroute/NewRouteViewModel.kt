package com.example.stepbystep.ui.newroute

import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.stepbystep.R
import com.example.stepbystep.util.StringFormatUtils

/**
 * ViewModel para la actividad NewRouteActivity.
 * 
 * Responsable de:
 * - Mantener el estado de la grabación de la ruta
 * - Calcular estadísticas como distancia, tiempo y elevación
 * - Gestionar la ruta de referencia
 * - Mantener un registro de puntos recopilados
 * - Procesar eventos del servicio de localización
 * 
 * Implementa un patrón observable para que la UI pueda reaccionar 
 * a cambios en estos datos.
 */
class NewRouteViewModel : ViewModel() {
    private val TAG = "RouteTrackingVM"

    /**
     * Clase para almacenar un punto de ubicación con su elevación
     */
    data class LocationPoint(
        val latLng: LatLng,
        val altitude: Double,
        val timestamp: Long = System.currentTimeMillis()
    )

    // Estado de grabación
    private val _isRecording = MutableLiveData(false)
    val isRecording: LiveData<Boolean> = _isRecording

    private val _isPaused = MutableLiveData(false)
    val isPaused: LiveData<Boolean> = _isPaused

    // Métricas de la ruta
    private val _currentDistance = MutableLiveData(0.0)
    val currentDistance: LiveData<Double> = _currentDistance

    val formattedDistance: LiveData<String> = _currentDistance.map { distance ->
        StringFormatUtils.formatDistanceKm(distance)
    }

    private val _elapsedTimeMs = MutableLiveData(0L)
    val elapsedTimeMs: LiveData<Long> = _elapsedTimeMs

    val formattedTime: LiveData<String> = _elapsedTimeMs.map { timeMs ->
        StringFormatUtils.formatDuration(timeMs)
    }

    private val _currentElevation = MutableLiveData(0.0)
    val currentElevation: LiveData<Double> = _currentElevation

    val formattedElevation: LiveData<String> = _currentElevation.map { elevation ->
        StringFormatUtils.formatElevation(elevation)
    }

    private val _elevationGain = MutableLiveData(0.0)
    val elevationGain: LiveData<Double> = _elevationGain

    val formattedElevationGain: LiveData<String> = _elevationGain.map { gain ->
        StringFormatUtils.formatElevationGain(gain)
    }

    // Propiedades para la ruta de referencia
    private val _referenceRoute = MutableLiveData<List<LatLng>>(emptyList())
    val referenceRoute: LiveData<List<LatLng>> = _referenceRoute

    private val _referenceRouteName = MutableLiveData<String>("")
    val referenceRouteName: LiveData<String> = _referenceRouteName

    private val _isReferenceRouteVisible = MutableLiveData<Boolean>(true)
    val isReferenceRouteVisible: LiveData<Boolean> = _isReferenceRouteVisible
    
    // Estado de visibilidad de componentes UI
    private val _statsVisible = MutableLiveData(true)
    val statsVisible: LiveData<Boolean> = _statsVisible
    
    private val _buttonsVisible = MutableLiveData(true)
    val buttonsVisible: LiveData<Boolean> = _buttonsVisible
    
    private val _autoTrackLocation = MutableLiveData(true)
    val autoTrackLocation: LiveData<Boolean> = _autoTrackLocation

    // Datos para el mapa y dibujo de ruta
    private val _routePoints = MutableLiveData<List<LatLng>>(mutableListOf())
    val routePoints: LiveData<List<LatLng>> = _routePoints

    // Datos internos
    val points = mutableListOf<LocationPoint>() // Lista de puntos recopilados
    private var lastElevation = 0.0             // Última elevación para calcular ganancia
    private var timerJob: Job? = null           // Trabajo para actualizar el tiempo
    private var startTime = 0L                  // Tiempo de inicio
    private var pausedAccumulated = 0L          // Tiempo acumulado en pausas
    private var pauseStart = 0L                 // Tiempo cuando se pausó

    // Para la visibilidad del chip de ruta de referencia
    val referenceRouteVisible: LiveData<Int> = _referenceRoute.map { route ->
        if (route.isEmpty()) View.GONE else View.VISIBLE
    }

    // Para el texto del botón start/stop
    val startStopButtonText: LiveData<Int> = _isRecording.map { isRecording ->
        if (isRecording) R.string.route_stop else R.string.route_start
    }

    // Para el texto del botón pause/resume
    val pauseResumeButtonText: LiveData<Int> = _isPaused.map { isPaused ->
        if (isPaused) R.string.route_resume else R.string.route_pause
    }

    // Para la visibilidad del botón pause/resume
    val pauseResumeButtonVisible: LiveData<Int> = _isRecording.map { isRecording ->
        if (isRecording) View.VISIBLE else View.GONE
    }

    /**
     * Restaura completamente el estado de tracking con datos del servicio
     */
    fun restoreTrackingState(
        distance: Double,
        timeMs: Long,
        elevation: Double,
        elevationGain: Double,
        routePoints: List<LatLng>
    ) {
        // Actualizar datos básicos
        _currentDistance.value = distance
        _elapsedTimeMs.value = timeMs
        _currentElevation.value = elevation
        _elevationGain.value = elevationGain
        
        // Restaurar puntos de la ruta en el mapa
        _routePoints.value = routePoints
        
        // Reconstruir lista de puntos con elevación
        points.clear()
        routePoints.forEach { latLng ->
            points.add(LocationPoint(latLng, elevation))
        }
        
        Log.d(TAG, "[VM] ✓ Estado restaurado completamente: " +
              "${routePoints.size} puntos, ${distance}km, " +
              "tiempo=${timeMs}ms")
    }

    /**
     * Inicia la grabación de una nueva ruta.
     * Reinicia todas las métricas y comienza el temporizador.
     */
    fun startRecording(forceReset: Boolean = true) {
        if (forceReset) {
            // Código original de reset
            _currentDistance.value = 0.0
            _elapsedTimeMs.value = 0L
            _currentElevation.value = 0.0
            _elevationGain.value = 0.0
            points.clear()
            clearRoutePoints()
        }
        
        // Resto del código para iniciar grabación...
        _isRecording.value = true
        _isPaused.value = false
        
        // Solo iniciar el timer si no hay uno en curso
        if (timerJob == null || timerJob?.isActive != true) {
            startTime = SystemClock.elapsedRealtime() - (_elapsedTimeMs.value ?: 0L)
            pausedAccumulated = 0L
            startTimer()
        }
    }

    /**
     * Pausa la grabación de la ruta.
     */
    fun pauseRecording() {
        if (_isRecording.value == true && _isPaused.value == false) {
            _isPaused.value = true
            pauseStart = SystemClock.elapsedRealtime()
            timerJob?.cancel()
        }
    }

    /**
     * Reanuda la grabación de la ruta después de una pausa.
     */
    fun resumeRecording() {
        if (_isRecording.value == true && _isPaused.value == true) {
            _isPaused.value = false
            pausedAccumulated += SystemClock.elapsedRealtime() - pauseStart
            // Reanudar timer
            timerJob?.cancel()
            timerJob = viewModelScope.launch(Dispatchers.Main) {
                while (_isRecording.value == true && _isPaused.value == false) {
                    val elapsed = SystemClock.elapsedRealtime() - startTime - pausedAccumulated
                    _elapsedTimeMs.value = elapsed
                    delay(500L)
                }
            }
        }
    }

    /**
     * Detiene la grabación de la ruta.
     */
    fun stopRecording() {
        _isRecording.value = false
        timerJob?.cancel()
    }

    /**
     * Actualiza los valores de tracking con los datos recibidos del servicio.
     */
    fun updateTracking(
        location: Location?,
        distance: Double,
        timeMs: Long,
        elevation: Double,
        elevationGain: Double
    ) {
        Log.d(TAG, "...") // tus logs

        // 1) Actualizar los LiveData
        _currentDistance.value  = distance
        _elapsedTimeMs.value    = timeMs
        _currentElevation.value = elevation
        _elevationGain.value    = elevationGain

        Log.d(TAG, "distancia=${_currentDistance.value}, " +
              "duracion=${_elapsedTimeMs.value}, " +
              "elevacionGanada=${_elevationGain.value}, " +
              "elevacion=${_currentElevation.value}")

        // 2) Si hay ubicación, agregar punto al mapa
        location?.let {
          val latLng = LatLng(it.latitude, it.longitude)
          addRoutePoint(latLng)
          points.add(LocationPoint(latLng, it.altitude))
        }

        // 3) (Opcional) Loguear estado tras la actualización
        Log.d(TAG, "[VM] ✅ LiveData actualizados: dist=${_currentDistance.value}, elev=${_currentElevation.value}, gain=${_elevationGain.value}")
    }

    /**
     * MÉTODO POTENCIALMENTE PROBLEMÁTICO
     */
    fun onLocationUpdated(location: Location) {
        Log.d(TAG, "[VM] ⚠️⚠️⚠️ onLocationUpdated llamado desde fuera del servicio ⚠️⚠️⚠️")
        Log.d(TAG, "[VM] Location: ${location.latitude}, ${location.longitude}, alt=${location.altitude}")
        Log.d(TAG, "[VM] Estado actual: recording=${_isRecording.value}, paused=${_isPaused.value}")
        
        // Este método no debería ejecutarse cuando estamos grabando,
        // ya que duplicaría la actualización de datos
        if (_isRecording.value == true) {
            Log.d(TAG, "[VM] ⚠️ IGNORANDO actualización directa porque estamos grabando")
            return
        }
        
        val oldElevation = _currentElevation.value ?: 0.0
        
        // Actualizar solo si no estamos grabando (el servicio gestiona ese caso)
        _currentElevation.value = location.altitude
        
        Log.d(TAG, "[VM] Actualización directa elevation: $oldElevation → ${location.altitude}")
    }

    /**
     * Agrega un punto a la ruta y notifica a los observadores
     */
    private fun addRoutePoint(point: LatLng) {
        // Crear una copia mutable de la lista actual o una nueva si es null
        val currentPoints = _routePoints.value?.toMutableList() ?: mutableListOf()
        
        // Añadir el nuevo punto
        currentPoints.add(point)
        
        // Muy importante: actualizar el LiveData con la NUEVA lista
        // Esto desencadena la notificación a los observadores
        _routePoints.postValue(currentPoints)
        
        // Log para depuración
        Log.d(TAG, "[ROUTE] ✅ Punto añadido al mapa: $point → Total: ${currentPoints.size}")
    }
    
    /**
     * Limpia todos los puntos de la ruta
     */
    fun clearRoutePoints() {
        _routePoints.value = mutableListOf()
    }

    /**
     * Establece la ruta de referencia a partir de una lista de puntos
     * 
     * @param points Lista de puntos LatLng que forman la ruta
     * @param name Nombre de la ruta de referencia
     */
    fun setReferenceRoute(points: List<LatLng>, name: String = "Ruta de referencia") {
        _referenceRoute.value = points
        _referenceRouteName.value = name
        _isReferenceRouteVisible.value = true
    }

    /**
     * Alterna la visibilidad de la ruta de referencia en el mapa
     */
    fun toggleReferenceRouteVisibility() {
        _isReferenceRouteVisible.value = _isReferenceRouteVisible.value?.not() ?: true
    }

    /**
     * Elimina la ruta de referencia
     */
    fun clearReferenceRoute() {
        _referenceRoute.value = emptyList()
        _referenceRouteName.value = ""
        _isReferenceRouteVisible.value = true
    }
    
    /**
     * Alterna el modo de seguimiento automático de ubicación
     */
    fun toggleAutoTrackLocation() {
        _autoTrackLocation.value = _autoTrackLocation.value?.not() ?: false
    }
    
    /**
     * Alterna la visibilidad del panel de estadísticas
     */
    fun toggleStatsVisibility() {
        _statsVisible.value = _statsVisible.value?.not() ?: false
    }
    
    /**
     * Alterna la visibilidad de los botones de control
     */
    fun toggleButtonsVisibility() {
        _buttonsVisible.value = _buttonsVisible.value?.not() ?: false
    }
    
    /**
     * Alterna la visibilidad de todos los elementos UI
     */
    fun toggleAllVisibility() {
        val shouldHideAll = _statsVisible.value == true || _buttonsVisible.value == true
        
        _statsVisible.value = !shouldHideAll
        _buttonsVisible.value = !shouldHideAll
    }

    /**
     * Prepara los datos para la pantalla de guardado de ruta
     * @return Bundle con todos los datos necesarios
     */
    fun prepareRouteDataForSave(): Bundle {
        val bundle = Bundle()
        bundle.putDouble("distance", _currentDistance.value ?: 0.0)
        bundle.putLong("duration", _elapsedTimeMs.value ?: 0L)
        bundle.putDouble("elevationGain", _elevationGain.value ?: 0.0)
        bundle.putDouble("elevation", _currentElevation.value ?: 0.0)
        
        val pointsList = ArrayList<Parcelable>(_routePoints.value ?: emptyList())
        bundle.putParcelableArrayList("points", pointsList)
        
        val altitudes = points.map { it.altitude }.toDoubleArray()
        bundle.putDoubleArray("altitudes", altitudes)

        //* Log al guardar para mostrar la distancia, duracion, elevacioGain, elevation */
        Log.d(TAG, "Preparando datos para guardar ruta: " +
              "distancia=${_currentDistance.value}, " +
              "duracion=${_elapsedTimeMs.value}, " +
              "elevacionGanada=${_elevationGain.value}, " +
              "elevacion=${_currentElevation.value}")
        
        return bundle
    }

    /**
     * Fuerza la actualización del valor de elevación
     */
    fun forceElevationUpdate(elevation: Double) {
        Log.d(TAG, "[VM] 🔄 forceElevationUpdate llamado con valor: $elevation")
        val oldElevation = _currentElevation.value ?: 0.0
        _currentElevation.value = elevation
        Log.d(TAG, "[VM] Elevation actualizada: $oldElevation → $elevation")
    }

    /**
     * Registra información de depuración sobre la ubicación actualizada
     */
    private fun logLocationUpdate(location: Location) {
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Ubicación: [${location.latitude}, ${location.longitude}], " +
                  "Precisión: ${location.accuracy}m, " +
                  "Velocidad: ${location.speed}m/s, " +
                  "Altitud: ${location.altitude}m")
        }
    }

    /**
     * Inicia el temporizador para actualizar el tiempo transcurrido
     */
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch(Dispatchers.Main) {
            while (_isRecording.value == true && _isPaused.value == false) {
                val elapsed = SystemClock.elapsedRealtime() - startTime - pausedAccumulated
                _elapsedTimeMs.value = elapsed
                delay(500L)
            }
        }
    }
}
