package com.example.stepbystep.util

import java.util.*
import java.util.concurrent.TimeUnit

object StringFormatUtils {

    /**
     * Format distance values in meters to appropriate units
     * - Shows as "Xm" for distances under 1km
     * - Shows as "X.X km" for distances 1km or more
     */
    fun formatDistance(distanceInMeters: Float): String {
        return if (distanceInMeters < 1000) {
            // Show as meters for distances less than 1km
            "${distanceInMeters.toInt()}m"
        } else {
            // Show as kilometers with 1 decimal place for distances 1km or more
            String.format(Locale.getDefault(), "%.1f km", distanceInMeters / 1000)
        }
    }
    
    /**
     * Overloaded version for Double type distance values in meters
     */
    @JvmStatic
    fun formatDistance(distanceInMeters: Double): String {
        return if (distanceInMeters < 1000) {
            // Show as meters for distances less than 1km
            "${distanceInMeters.toInt()}m"
        } else {
            // Show as kilometers with 1 decimal place for distances 1km or more
            String.format(Locale.getDefault(), "%.1f km", distanceInMeters / 1000)
        }
    }

    /**
     * Format distance values that are already in kilometers
     * Shows as meters for distances under 1km
     * Shows as kilometers for distances of 1km or more
     */
    @JvmStatic
    fun formatDistanceKm(distanceKm: Double): String {
        return if (distanceKm < 1.0) {
            // Distancias menores a 1km, mostrar en metros
            "${(distanceKm * 1000).toInt()}m"
        } else {
            // Distancias de 1km o más, mostrar en km
            String.format(Locale.getDefault(), "%.1f km", distanceKm)
        }
    }

    /**
     * Format time in milliseconds to a human-readable duration
     * - Shows "Xh" for whole hours
     * - Shows "Xh Ym" for hours with minutes
     * - Shows "Xm Ys" for times under 1 hour
     */
    @JvmStatic
    fun formatDuration(timeMs: Long): String {
        val hours = TimeUnit.MILLISECONDS.toHours(timeMs)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(timeMs) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(timeMs) % 60
        
        return when {
            hours > 0 && minutes == 0L && seconds == 0L -> 
                // Just hours: "1h"
                String.format(Locale.getDefault(), "%dh", hours)
                
            hours > 0 && seconds == 0L -> 
                // Hours and minutes: "1h 30m"
                String.format(Locale.getDefault(), "%dh %dm", hours, minutes)
                
            hours > 0 -> 
                // Full format with seconds: "1h 30m 45s"
                String.format(Locale.getDefault(), "%dh %dm %ds", hours, minutes, seconds)
                
            minutes > 0 && seconds == 0L -> 
                // Just minutes: "30m"
                String.format(Locale.getDefault(), "%dm", minutes)
                
            else -> 
                // Minutes and seconds: "30m 45s"
                String.format(Locale.getDefault(), "%dm %ds", minutes, seconds)
        }
    }

    /**
     * Format elevation in meters with appropriate unit
     */
    fun formatElevation(elevationMeters: Float): String {
        return "${elevationMeters.toInt()} m"
    }
    
    /**
     * Overloaded version for Double type elevation values
     */
    @JvmStatic
    fun formatElevation(elevationMeters: Double): String {
        return "${elevationMeters.toInt()} m"
    }

    /**
     * Format elevation gain with plus sign to indicate gain
     */
    fun formatElevationGain(elevationGainMeters: Float): String {
        return "+${elevationGainMeters.toInt()} m"
    }
    
    /**
     * Overloaded version for Double type elevation gain values
     */
    @JvmStatic
    fun formatElevationGain(elevationGainMeters: Double): String {
        return "+${elevationGainMeters.toInt()} m"
    }
}
