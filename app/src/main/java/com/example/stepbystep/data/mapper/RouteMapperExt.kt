package com.example.stepbystep.data.mapper

import com.example.stepbystep.data.local.PointEntity
import com.example.stepbystep.data.local.RouteEntity
import com.example.stepbystep.data.local.RouteWithPoints
import com.example.stepbystep.domain.model.Point
import com.example.stepbystep.domain.model.Route
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE

// Convert database model to domain model
fun RouteWithPoints.toDomain(): Route {
    return Route(
        id = route.id,
        name = route.name,
        description = route.description ?: "",
        date = route.date, // Keep as string
        distance = route.distance,
        duration = route.duration,
        elevation = route.elevation,
        elevationGain = route.elevationGain,
        points = points.map { it.toDomain() }
    )
}

// Convert a point entity to domain model
fun PointEntity.toDomain(): Point {
    return Point(
        latitude = latitude,
        longitude = longitude,
        altitude = altitude,
        timestamp = timestamp
    )
}

// Convert domain model to entity
fun Route.toRouteEntity(): RouteEntity {
    return RouteEntity(
        id = id,
        name = name,
        description = description,
        date = date, // Keep as string
        distance = distance,
        duration = duration,
        elevation = elevation,
        elevationGain = elevationGain
    )
}

// Convert domain point to entity
fun Point.toEntity(routeId: Long): PointEntity {
    return PointEntity(
        routeId = routeId,
        latitude = latitude,
        longitude = longitude,
        altitude = altitude,
        timestamp = timestamp
    )
}

// Helper function to convert a Route to entities for database insertion
fun Route.toEntities(): Pair<RouteEntity, List<PointEntity>> {
    val routeEntity = this.toRouteEntity()
    val pointEntities = this.points.map { it.toEntity(routeEntity.id) }
    return Pair(routeEntity, pointEntities)
}