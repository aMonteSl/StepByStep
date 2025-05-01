package com.example.stepbystep.util

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.example.stepbystep.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions

/**
 * Utilidades para manejar y configurar mapas de Google Maps.
 * 
 * Proporciona funciones de extensión para Google Maps que facilitan
 * tareas comunes como aplicar estilos según el modo oscuro/claro.
 */
object MapUtils {
    /**
     * Configura el estilo del mapa según el modo de interfaz del dispositivo (día/noche).
     * Aplica el estilo oscuro si está en modo noche.
     * 
     * Esta función es una extensión de GoogleMap, por lo que se puede llamar
     * directamente desde una instancia de GoogleMap.
     * 
     * @param context Contexto necesario para acceder a los recursos y configuración
     */
    fun GoogleMap.configureMapStyle(context: Context) {
        val isNightMode = (context.resources.configuration.uiMode and 
                          Configuration.UI_MODE_NIGHT_MASK) == 
                          Configuration.UI_MODE_NIGHT_YES
        
        if (isNightMode) {
            try {
                val success = setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_dark)
                )
                if (!success) {
                    Log.e("MapStyle", "Error al aplicar el estilo del mapa.")
                }
            } catch (e: Exception) {
                Log.e("MapStyle", "No se puede encontrar el estilo. Error: ", e)
            }
        } else {
            // Restablecer al estilo predeterminado si no está en modo noche
            setMapStyle(null)
        }
    }
}