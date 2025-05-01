package com.example.stepbystep.ui.newroute

import android.location.Location
import android.os.SystemClock
import androidx.lifecycle.*
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewRouteViewModel : ViewModel() {

    // Añade esta clase interna para almacenar puntos con elevación
    data class LocationPoint(
        val latLng: LatLng,
        val altitude: Double
    )

    private val _isRecording     = MutableLiveData(false)
    val isRecording: LiveData<Boolean>    = _isRecording

    private val _isPaused        = MutableLiveData(false)
    val isPaused: LiveData<Boolean>       = _isPaused

    private val _currentDistance = MutableLiveData(0.0)
    val currentDistance: LiveData<Double> = _currentDistance

    private val _elapsedTimeMs   = MutableLiveData(0L)
    val elapsedTimeMs: LiveData<Long>     = _elapsedTimeMs

    private val _currentElevation  = MutableLiveData(0.0)
    val currentElevation: LiveData<Double> = _currentElevation

    private val _elevationGain   = MutableLiveData(0.0)
    val elevationGain: LiveData<Double>    = _elevationGain

    // Añadir para la ruta de referencia
    private val _referencePoints = MutableLiveData<List<LatLng>>()
    val referencePoints: LiveData<List<LatLng>> = _referencePoints

    private val _referenceRouteVisible = MutableLiveData(true)
    val referenceRouteVisible: LiveData<Boolean> = _referenceRouteVisible

    private val _referenceRouteName = MutableLiveData<String>()
    val referenceRouteName: LiveData<String> = _referenceRouteName

    // Datos internos
    val points = mutableListOf<LocationPoint>()
    private var lastElevation = 0.0
    private var timerJob: Job? = null
    private var startTime = 0L
    private var pausedAccumulated = 0L
    private var pauseStart = 0L

    fun startRecording() {
        // Reiniciar métricas
        _currentDistance.value = 0.0
        _elapsedTimeMs.value   = 0L
        _currentElevation.value= 0.0
        _elevationGain.value   = 0.0
        points.clear()
        lastElevation     = 0.0
        pausedAccumulated = 0L
        _isPaused.value   = false
        _isRecording.value= true

        startTime = SystemClock.elapsedRealtime()
        timerJob?.cancel()
        timerJob = viewModelScope.launch(Dispatchers.Main) {
            while (_isRecording.value == true && _isPaused.value == false) {
                val elapsed = SystemClock.elapsedRealtime() - startTime - pausedAccumulated
                _elapsedTimeMs.value = elapsed
                delay(500L)
            }
        }
    }

    fun pauseRecording() {
        if (_isRecording.value == true && _isPaused.value == false) {
            _isPaused.value = true
            pauseStart = SystemClock.elapsedRealtime()
            timerJob?.cancel()
        }
    }

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

    fun stopRecording() {
        _isRecording.value = false
        timerJob?.cancel()
    }

    /**
     * Llamar desde la Activity cuando llega una nueva locación GPS.
     */
    fun onLocationUpdated(location: Location) {
        if (_isRecording.value != true || _isPaused.value == true) return

        val latLng = LatLng(location.latitude, location.longitude)
        val locationPoint = LocationPoint(latLng, location.altitude)
        
        if (points.isNotEmpty()) {
            // Distancia incremental en km
            val last = points.last().latLng
            val delta = SphericalUtil.computeDistanceBetween(last, latLng) / 1000.0  // Convierte a km
            _currentDistance.value = (_currentDistance.value ?: 0.0) + delta
        }
        
        points.add(locationPoint)

        // Elevación
        _currentElevation.value = location.altitude
        if (points.size == 1) {
            lastElevation = location.altitude
        } else {
            val gain = location.altitude - lastElevation
            if (gain > 0) {
                _elevationGain.value = (_elevationGain.value ?: 0.0) + gain
            }
            lastElevation = location.altitude
        }
    }

    // Método para establecer los datos de la ruta de referencia
    fun setReferenceRoute(points: List<LatLng>, name: String = "Ruta de referencia") {
        _referencePoints.value = points
        _referenceRouteName.value = name
    }
    
    // Método para alternar la visibilidad de la ruta de referencia
    fun toggleReferenceRouteVisibility() {
        _referenceRouteVisible.value = !(_referenceRouteVisible.value ?: true)
    }
    
    // Método para limpiar la ruta de referencia
    fun clearReferenceRoute() {
        _referencePoints.value = emptyList()
        _referenceRouteName.value = null
    }
}
