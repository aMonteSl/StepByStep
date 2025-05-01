package com.example.stepbystep.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepbystep.data.repository.RouteRepository
import com.example.stepbystep.data.local.RouteRoomDatabase
import com.example.stepbystep.domain.model.Route
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * ViewModel para la actividad principal.
 * Gestiona la lógica de negocio y el acceso a datos para la pantalla principal,
 * manteniendo el estado de la UI durante cambios de configuración.
 */
class MainViewModel(context: Context) : ViewModel() {
    private val repository: RouteRepository
    
    // Inicialización del repositorio a partir de la base de datos
    init {
        val database = RouteRoomDatabase.getInstance(context)
        repository = RouteRepository(database.routeDao())
    }

    // LiveData observable que contiene la lista de rutas
    val routes: LiveData<List<Route>> = repository.routes
    private var nextId = 1L

    /**
     * Método para añadir una ruta de prueba con datos aleatorios.
     * Útil para propósitos de desarrollo y demostración.
     */
    fun addDummyRoute() {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatter.format(Date())
        val route = Route(
            id = nextId,
            name = "Ruta $nextId",
            date = date,
            distance = (1..10).random().toDouble(),
            duration = (10..120).random() * 60_000L,
            elevation = (50..300).random().toDouble(),
            elevationGain = (0..200).random().toDouble()
        )
        nextId++
        
        // Lanzar en viewModelScope ya que addRoute es una función suspendida
        viewModelScope.launch {
            repository.addRoute(route)
        }
    }

    /**
     * Elimina una ruta específica de la base de datos.
     * Los cambios se reflejarán automáticamente en la UI gracias a LiveData.
     *
     * @param route La ruta que se desea eliminar
     */
    fun deleteRoute(route: Route) {
        viewModelScope.launch {
            repository.deleteRoute(route)
            // No necesitamos actualizar la lista de rutas manualmente aquí
            // porque ya estamos observando el flujo de datos del repositorio
        }
    }
}
