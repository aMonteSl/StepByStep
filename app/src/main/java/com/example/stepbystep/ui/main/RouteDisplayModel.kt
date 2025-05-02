package com.example.stepbystep.ui.main

import com.example.stepbystep.domain.model.Route
import com.example.stepbystep.util.DateFormatUtils
import com.example.stepbystep.util.StringFormatUtils

/**
 * Modelo de presentación para mostrar rutas en la UI.
 * Contiene propiedades ya formateadas para presentación directa.
 */
data class RouteDisplayModel(
    val id: Long,
    val name: String,
    val description: String,
    val formattedDate: String,
    val formattedDistance: String,
    val formattedDuration: String,
    val formattedElevation: String,
    val formattedElevationGain: String,
    val imagePath: String?,
    val route: Route  // Mantener referencia al objeto original
) {
    companion object {
        fun fromRoute(route: Route): RouteDisplayModel {
            return RouteDisplayModel(
                id = route.id,
                name = route.name,
                description = route.description,
                formattedDate = DateFormatUtils.formatDate(route.date),
                formattedDistance = StringFormatUtils.formatDistanceKm(route.distance),
                formattedDuration = StringFormatUtils.formatDuration(route.duration),
                formattedElevation = StringFormatUtils.formatElevation(route.elevation),
                formattedElevationGain = StringFormatUtils.formatElevationGain(route.elevationGain),
                imagePath = route.imagePath,
                route = route
            )
        }
    }
}