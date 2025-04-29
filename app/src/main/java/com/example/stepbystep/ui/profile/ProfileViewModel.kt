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
        
        // Distancia total
        val total = routes.sumOf { it.distance }
        _totalDistance.value = StringFormatUtils.formatDistanceKm(total)
        
        // Distancia media por actividad
        val avg = total / routes.size
        _averageDistance.value = StringFormatUtils.formatDistanceKm(avg)
        
        // Distancia máxima
        val max = routes.maxByOrNull { it.distance }?.distance ?: 0.0
        _maxDistance.value = StringFormatUtils.formatDistanceKm(max)
        
        // Tiempo total
        val totalTimeMs = routes.sumOf { it.duration }
        _totalDuration.value = formatLongDuration(totalTimeMs)
        
        // Ritmo medio (minutos por km)
        val totalMinutes = TimeUnit.MILLISECONDS.toMinutes(totalTimeMs)
        val paceMinPerKm = if (total > 0) totalMinutes / total else 0.0
        val paceMinutes = paceMinPerKm.toInt()
        val paceSeconds = ((paceMinPerKm - paceMinutes) * 60).toInt()
        _averagePace.value = String.format("%d:%02d min/km", paceMinutes, paceSeconds)
        
        // Desnivel acumulado
        val totalElevation = routes.sumOf { it.elevationGain }
        _totalElevationGain.value = StringFormatUtils.formatElevationGain(totalElevation)
    }
    
    private fun setEmptyStatistics() {
        _totalActivities.value = 0
        _firstActivityDate.value = "No hay actividades"
        _totalDistance.value = "0 km"
        _averageDistance.value = "0 km"
        _maxDistance.value = "0 km"
        _totalDuration.value = "0h 0m"
        _averagePace.value = "0:00 min/km"
        _totalElevationGain.value = "+0 m"
    }
    
    private fun formatLongDuration(durationMs: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(durationMs)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(durationMs) % 60
        return "${hours}h ${minutes}m"
    }
}

