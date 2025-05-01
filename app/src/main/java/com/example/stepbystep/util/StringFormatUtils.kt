package com.example.stepbystep.util

import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

/**
 * Utilidad para formatear valores numéricos como distancias, tiempos y elevaciones.
 * 
 * Provee métodos para convertir valores numéricos a cadenas de texto formateadas
 * con la unidad apropiada y nivel de precisión según el contexto de uso.
 */
object StringFormatUtils {

    /**
     * Formatea valores de distancia en metros a las unidades apropiadas
     * - Muestra "Xm" para distancias menores a 1km
     * - Muestra "X.X km" para distancias de 1km o más
     * 
     * @param distanceInMeters Distancia en metros
     * @return Cadena formateada con la unidad apropiada
     */
    @JvmStatic
    fun formatDistance(distanceInMeters: Float): String {
        // Siempre usar 2 decimales para mayor precisión y consistencia
        return if (distanceInMeters < 1000) {
            // Para distancias menores a 1km, mostrar en metros con 2 decimales
            String.format(Locale.getDefault(), "%.2f m", distanceInMeters)
        } else {
            // Para distancias mayores o iguales a 1km, mostrar en kilómetros
            String.format(Locale.getDefault(), "%.2f km", distanceInMeters / 1000)
        }
    }
    
    /**
     * Versión sobrecargada para valores de distancia en metros de tipo Double
     * 
     * @param distanceInMeters Distancia en metros
     * @return Cadena formateada con la unidad apropiada
     */
    @JvmStatic
    fun formatDistance(distanceInMeters: Double): String {
        return if (distanceInMeters < 1000) {
            // Mostrar como metros para distancias menores a 1km
            "${distanceInMeters.toInt()}m"
        } else {
            // Mostrar como kilómetros con 1 decimal para distancias de 1km o más
            String.format(Locale.getDefault(), "%.1f km", distanceInMeters / 1000)
        }
    }

    /**
     * Formatea valores de distancia que ya están en kilómetros
     * - Muestra metros con precisión decimal para distancias muy cortas
     * - Muestra kilómetros con 2 decimales para mayor precisión
     * 
     * @param distanceKm Distancia en kilómetros
     * @return Cadena formateada con la unidad apropiada
     */
    @JvmStatic
    fun formatDistanceKm(distanceKm: Double): String {
        return if (distanceKm < 1.0) {
            // Mostrar metros con precisión decimal para distancias muy cortas
            val meters = distanceKm * 1000
            if (meters < 10) {
                // Para distancias muy cortas, mostramos 2 decimales
                String.format(Locale.getDefault(), "%.2f m", meters)
            } else {
                // Redondeamos a enteros solo para distancias mayores a 10m
                "${meters.roundToInt()} m"
            }
        } else {
            // Para kilometraje, usar 2 decimales para mayor precisión
            String.format(Locale.getDefault(), "%.2f km", distanceKm)
        }
    }

    /**
     * Formatea tiempo en milisegundos a una duración legible para humanos
     * - Muestra "Xh" para horas completas
     * - Muestra "Xh Ym" para horas con minutos
     * - Muestra "Xm Ys" para tiempos menores a 1 hora
     * 
     * @param timeMs Tiempo en milisegundos
     * @return Cadena con la duración formateada
     */
    @JvmStatic
    fun formatDuration(timeMs: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(timeMs)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeMs) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeMs) % 60
        
        return when {
            hours > 0 && minutes == 0L && seconds == 0L -> 
                // Solo horas: "1h"
                String.format(Locale.getDefault(), "%dh", hours)
                
            hours > 0 && seconds == 0L -> 
                // Horas y minutos: "1h 30m"
                String.format(Locale.getDefault(), "%dh %dm", hours, minutes)
                
            hours > 0 -> 
                // Formato completo con segundos: "1h 30m 45s"
                String.format(Locale.getDefault(), "%dh %dm %ds", hours, minutes, seconds)
                
            minutes > 0 && seconds == 0L -> 
                // Solo minutos: "30m"
                String.format(Locale.getDefault(), "%dm", minutes)
                
            else -> 
                // Minutos y segundos: "30m 45s"
                String.format(Locale.getDefault(), "%dm %ds", minutes, seconds)
        }
    }

    /**
     * Formatea elevación en metros con la unidad apropiada
     * 
     * @param elevationMeters Elevación en metros
     * @return Cadena formateada (ej: "125 m")
     */
    @JvmStatic
    fun formatElevation(elevationMeters: Float): String {
        return "${elevationMeters.toInt()} m"
    }
    
    /**
     * Versión sobrecargada para valores de elevación de tipo Double
     * 
     * @param elevationMeters Elevación en metros
     * @return Cadena formateada (ej: "125 m")
     */
    @JvmStatic
    fun formatElevation(elevationMeters: Double): String {
        return "${elevationMeters.roundToInt()} m"
    }

    /**
     * Formatea ganancia de elevación con signo positivo para indicar ganancia
     * 
     * @param elevationGainMeters Ganancia de elevación en metros
     * @return Cadena formateada (ej: "+125 m")
     */
    @JvmStatic
    fun formatElevationGain(elevationGainMeters: Float): String {
        return "+${elevationGainMeters.toInt()} m"
    }
    
    /**
     * Versión sobrecargada para valores de ganancia de elevación de tipo Double
     * 
     * @param elevationGainMeters Ganancia de elevación en metros
     * @return Cadena formateada (ej: "+125 m")
     */
    @JvmStatic
    fun formatElevationGain(elevationGainMeters: Double): String {
        return "+${elevationGainMeters.toInt()} m"
    }
}
