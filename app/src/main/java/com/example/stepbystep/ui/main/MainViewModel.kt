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

class MainViewModel(context: Context) : ViewModel() {
    private val repository: RouteRepository
    
    init {
        val database = RouteRoomDatabase.getInstance(context)
        repository = RouteRepository(database.routeDao())
    }

    val routes: LiveData<List<Route>> = repository.routes
    private var nextId = 1L

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
        
        // Launch in viewModelScope since addRoute is a suspend function
        viewModelScope.launch {
            repository.addRoute(route)
        }
    }
}
