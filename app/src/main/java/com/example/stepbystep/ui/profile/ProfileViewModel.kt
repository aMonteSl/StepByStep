package com.example.stepbystep.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepbystep.data.local.RouteRoomDatabase
import com.example.stepbystep.data.local.UserPreferences
import com.example.stepbystep.data.repository.RouteRepository
import com.example.stepbystep.domain.model.Route
import com.example.stepbystep.util.DateFormatUtils
import com.example.stepbystep.util.StringFormatUtils
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

class ProfileViewModel(context: Context) : ViewModel() {
    
    private val repository: RouteRepository
    private val userPrefs: UserPreferences = UserPreferences(context)
    
    init {
        val database = RouteRoomDatabase.getInstance(context)
        repository = RouteRepository(database.routeDao())
    }
    
    // Datos del atleta
    private val _athleteName = MutableLiveData(userPrefs.athleteName)
    val athleteName: LiveData<String> = _athleteName
    
    private val _athleteDescription = MutableLiveData(userPrefs.athleteDescription)
    val athleteDescription: LiveData<String> = _athleteDescription
    
    private val _isEditMode = MutableLiveData<Boolean>(false)  // Inicializado con false
    val isEditMode: LiveData<Boolean> = _isEditMode
    
    // Estadísticas en vivo que se actualizarán cuando cambien las rutas
    private val _totalActivities = MutableLiveData(0)
    val totalActivities: LiveData<Int> = _totalActivities
    
    private val _firstActivityDate = MutableLiveData<String>()
    val firstActivityDate: LiveData<String> = _firstActivityDate
    
    private val _totalDistance = MutableLiveData<String>()
    val totalDistance: LiveData<String> = _totalDistance
    
    private val _averageDistance = MutableLiveData<String>()
    val averageDistance: LiveData<String> = _averageDistance
    
    private val _maxDistance = MutableLiveData<String>()
    val maxDistance: LiveData<String> = _maxDistance
    
    private val _totalDuration = MutableLiveData<String>()
    val totalDuration: LiveData<String> = _totalDuration
    
    private val _averagePace = MutableLiveData<String>()
    val averagePace: LiveData<String> = _averagePace
    
    private val _totalElevationGain = MutableLiveData<String>()
    val totalElevationGain: LiveData<String> = _totalElevationGain
    
    init {
        // Observar las rutas y actualizar estadísticas cuando cambien
        viewModelScope.launch {
            repository.routes.observeForever { routes ->
                calculateStatistics(routes)
            }
        }
    }
    
    fun toggleEditMode() {
        _isEditMode.value = !(_isEditMode.value ?: false)
    }
    
    fun updateProfile(name: String, description: String) {
        userPrefs.athleteName = name
        userPrefs.athleteDescription = description
        _athleteName.value = name
        _athleteDescription.value = description
        _isEditMode.value = false
    }
    
    private fun calculateStatistics(routes: List<Route>) {
        if (routes.isEmpty()) {
            setEmptyStatistics()
            return
        }
        
        // Número total de actividades
        _totalActivities.value = routes.size
        
        // Fecha de la primera actividad
        val oldestDate = routes.minByOrNull { it.date }?.date ?: ""
        _firstActivityDate.value = DateFormatUtils.formatDate(oldestDate)
        
        // Distancia total (en km)
        val totalKm = routes.sumOf { it.distance }
        _totalDistance.value = StringFormatUtils.formatDistanceKm(totalKm)
        
        // Distancia media por actividad (en km)
        val avgKm = if (routes.isNotEmpty()) totalKm / routes.size else 0.0
        _averageDistance.value = StringFormatUtils.formatDistanceKm(avgKm)
        
        // Distancia máxima (en km)
        val maxKm = routes.maxOfOrNull { it.distance } ?: 0.0
        _maxDistance.value = StringFormatUtils.formatDistanceKm(maxKm)
        
        // Tiempo total (en ms)
        val totalTimeMs = routes.sumOf { it.duration }
        _totalDuration.value = formatLongDuration(totalTimeMs)
        
        // Cálculo de ritmo mejorado: Calculamos ritmo ponderado por distancia
        if (totalKm > 0) {
            // Convertir a segundos totales para mayor precisión
            val totalSeconds = TimeUnit.MILLISECONDS.toSeconds(totalTimeMs)
            // Ritmo en segundos por km
            val paceSecsPerKm = totalSeconds / totalKm
            
            // Convertir a formato minutos:segundos
            val paceMinutes = (paceSecsPerKm / 60).toInt()
            val paceSeconds = (paceSecsPerKm % 60).toInt()
            _averagePace.value = String.format("%d:%02d min/km", paceMinutes, paceSeconds)
        } else {
            _averagePace.value = "0:00 min/km"
        }
        
        // Desnivel acumulado (en metros)
        val totalElevation = routes.sumOf { it.elevationGain }
        _totalElevationGain.value = StringFormatUtils.formatElevationGain(totalElevation)
    }
    
    private fun setEmptyStatistics() {
        _totalActivities.value = 0
        _firstActivityDate.value = "No hay actividades"
        _totalDistance.value = "0 m"
        _averageDistance.value = "0 m"
        _maxDistance.value = "0 m"
        _totalDuration.value = "0h 0m 0s"  // Añadidos los segundos
        _averagePace.value = "0:00 min/km"
        _totalElevationGain.value = "+0 m"
    }
    
    private fun formatLongDuration(durationMs: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(durationMs)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(durationMs) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(durationMs) % 60
        return "${hours}h ${minutes}m ${seconds}s"
    }
}

