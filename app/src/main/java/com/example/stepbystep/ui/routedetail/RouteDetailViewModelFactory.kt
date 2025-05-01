package com.example.stepbystep.ui.routedetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory para crear instancias de RouteDetailViewModel.
 * 
 * Esta clase es necesaria porque necesitamos pasar el contexto
 * al constructor del ViewModel, y los ViewModels no pueden recibir
 * parámetros directamente desde la actividad.
 */
class RouteDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    /**
     * Crea una nueva instancia del ViewModel solicitado.
     * 
     * @param modelClass Clase del ViewModel a crear
     * @return Instancia del ViewModel configurada con el contexto
     * @throws IllegalArgumentException si la clase solicitada no es compatible
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RouteDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RouteDetailViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}