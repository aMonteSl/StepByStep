package com.example.stepbystep.data.mapper

import com.example.stepbystep.data.local.PointEntity
import com.example.stepbystep.data.local.RouteEntity
import com.example.stepbystep.data.local.RouteWithPoints
import com.example.stepbystep.domain.model.Point
import com.example.stepbystep.domain.model.Route
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object RouteMapper {
    private val DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE

    fun RouteWithPoints.toDomain(): Route {
        return Route(
            id = route.id,
            name = route.name,
            description = route.description ?: "",  // Mantener el manejo de nulos
            date = route.date,
            distance = route.distance,
            duration = route.duration,
            elevation = route.elevation,
            elevationGain = route.elevationGain,
            imagePath = route.imagePath,
            points = points.map { it.toDomain() }
        )
    }

    fun Route.toEntities(): Pair<RouteEntity, List<PointEntity>> {
        val routeEntity = RouteEntity(
            id = id,
            name = name,
            description = description,
            date = date,
            distance = distance,
            duration = duration,
            elevation = elevation,
            elevationGain = elevationGain,
            imagePath = imagePath  
        )
        
        val pointEntities = points.map { 
            PointEntity(
                routeId = id,
                latitude = it.latitude,
                longitude = it.longitude,
                altitude = it.altitude,
                timestamp = it.timestamp
            )
        }
        
        return Pair(routeEntity, pointEntities)
    }

    fun PointEntity.toDomain(): Point {
        return Point(
            latitude = latitude,
            longitude = longitude,
            altitude = altitude,
            timestamp = timestamp
        )
    }
    
    fun fromEntity(rwp: RouteWithPoints): Route = rwp.toDomain()
    
    fun toEntity(route: Route): Pair<RouteEntity, List<PointEntity>> = route.toEntities()
}
