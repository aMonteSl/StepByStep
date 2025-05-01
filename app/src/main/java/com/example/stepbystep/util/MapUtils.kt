package com.example.stepbystep.util

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.example.stepbystep.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions

object MapUtils {
    /**
     * Configures the map style based on the device's UI mode (day/night)
     * Applies the dark style if in night mode
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
                    Log.e("MapStyle", "Style parsing failed.")
                }
            } catch (e: Exception) {
                Log.e("MapStyle", "Can't find style. Error: ", e)
            }
        } else {
            // Reset to default style if not in night mode
            setMapStyle(null)
        }
    }
}