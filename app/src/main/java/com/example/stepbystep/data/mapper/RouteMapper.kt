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

    /** De Room (RouteWithPoints) a dominio (Route) */
    fun fromEntity(rwp: RouteWithPoints): Route {
        val entity = rwp.route
        val pts = rwp.points.map { pe -> Point(
            latitude  = pe.latitude,
            longitude = pe.longitude,
            altitude  = pe.altitude,
            timestamp = pe.timestamp
        )}
        return Route(
            id           = entity.id,
            name         = entity.name,
            description  = entity.description ?: "",  // Handle nullable description
            date         = entity.date,  
            distance     = entity.distance,
            duration     = entity.duration,
            elevation    = entity.elevation,
            elevationGain = entity.elevationGain,
            points       = pts
        )
    }

    /** De dominio (Route) a Room (RouteEntity + PointEntity) */
    fun toEntity(route: Route): Pair<RouteEntity, List<PointEntity>> {
        val re = RouteEntity(
            id           = route.id,
            name         = route.name,
            description  = route.description,
            date         = route.date,  // Keep as string, no conversion needed
            distance     = route.distance,
            duration     = route.duration,
            elevation    = route.elevation,
            elevationGain = route.elevationGain
        )
        val peList = route.points.map { pt ->
            PointEntity(
                routeId   = re.id,
                latitude  = pt.latitude,
                longitude = pt.longitude,
                altitude  = pt.altitude,
                timestamp = pt.timestamp
            )
        }
        return re to peList
    }

    fun RouteWithPoints.toDomain(): Route {
        return Route(
            id = route.id,
            name = route.name,
            description = route.description,
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
}
