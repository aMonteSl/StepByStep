package com.example.stepbystep.ui.saveroute

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory para crear instancias de SaveRouteViewModel.
 * 
 * Esta clase es necesaria porque el ViewModel necesita un contexto
 * para acceder a la base de datos, y los ViewModels no pueden recibir
 * parámetros directamente desde las actividades.
 * 
 * @param context Contexto de la aplicación necesario para acceder a la base de datos
 */
class SaveRouteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    /**
     * Crea una nueva instancia del ViewModel solicitado.
     * 
     * @param modelClass Clase del ViewModel que se desea crear
     * @return Instancia del ViewModel configurada con el contexto
     * @throws IllegalArgumentException si se solicita una clase de ViewModel desconocida
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaveRouteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SaveRouteViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}