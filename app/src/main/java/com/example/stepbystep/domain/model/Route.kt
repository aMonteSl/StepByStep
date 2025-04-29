package com.example.stepbystep.domain.model

import java.time.LocalDate

data class Route(
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val date: String,
    val distance: Double,
    val duration: Long,
    val elevation: Double = 0.0,
    val elevationGain: Double = 0.0,
    val imagePath: String? = null, // Ruta de la imagen
    val points: List<Point> = emptyList()
) {
    // Alternate field names as computed properties
    val distanceMeters: Double get() = distance * 1000
    val distanceKm: Double get() = distance
    val durationMs: Long get() = duration
    val elevationM: Double get() = elevation
}
