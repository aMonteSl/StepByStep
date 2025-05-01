package com.example.stepbystep.data.local

import android.content.Context
import android.content.SharedPreferences

/**
 * Clase que gestiona las preferencias del usuario mediante SharedPreferences.
 * Permite almacenar y recuperar información básica del usuario que persiste
 * entre sesiones de la aplicación.
 */
class UserPreferences(context: Context) {
    
    // Instancia de SharedPreferences para almacenamiento persistente
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    /**
     * Nombre del atleta/usuario.
     * Por defecto es "Atleta" si no se ha establecido un valor personalizado.
     */
    var athleteName: String
        get() = prefs.getString(KEY_ATHLETE_NAME, "Atleta") ?: "Atleta"
        set(value) = prefs.edit().putString(KEY_ATHLETE_NAME, value).apply()
    
    /**
     * Descripción o biografía del atleta/usuario.
     * Por defecto es una cadena vacía.
     */
    var athleteDescription: String
        get() = prefs.getString(KEY_ATHLETE_DESCRIPTION, "") ?: ""
        set(value) = prefs.edit().putString(KEY_ATHLETE_DESCRIPTION, value).apply()
    
    companion object {
        // Constantes para identificar el archivo de preferencias y sus claves
        private const val PREFS_NAME = "user_preferences"
        private const val KEY_ATHLETE_NAME = "athlete_name"
        private const val KEY_ATHLETE_DESCRIPTION = "athlete_description"
    }
}