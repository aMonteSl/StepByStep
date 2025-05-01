package com.example.stepbystep.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory para crear instancias de MainViewModel con un constructor que recibe un contexto.
 * El ViewModel se encargará internamente de crear el repositorio necesario.
 * Esto permite desacoplar la creación del ViewModel de sus dependencias.
 */
class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    /**
     * Crea una nueva instancia del ViewModel solicitado.
     * @param modelClass Clase del ViewModel que se quiere crear
     * @return Una instancia del ViewModel solicitado
     * @throws IllegalArgumentException si la clase no es un MainViewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(context) as T
        }
        throw IllegalArgumentException("Clase de ViewModel desconocida")
    }
}
