package com.example.stepbystep.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory para crear instancias de ProfileViewModel.
 * 
 * Necesaria para poder pasar el contexto al constructor del ViewModel,
 * ya que los ViewModels no pueden recibir parámetros directamente.
 * 
 * @param context Contexto necesario para acceder a preferencias y base de datos
 */
class ProfileViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    /**
     * Crea una instancia del ViewModel solicitado
     * 
     * @param modelClass Clase del ViewModel que se quiere crear
     * @return Instancia configurada del ViewModel
     * @throws IllegalArgumentException si se solicita un tipo de ViewModel desconocido
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}