package com.example.stepbystep.data.mapper

import com.example.stepbystep.data.local.PointEntity
import com.example.stepbystep.data.local.RouteEntity
import com.example.stepbystep.data.local.RouteWithPoints
import com.example.stepbystep.domain.model.Point
import com.example.stepbystep.domain.model.Route
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Objeto utilizado para mapear (convertir) entre las entidades de la base de datos
 * y los modelos de dominio. Facilita la separación entre la capa de datos y la lógica
 * de negocio de la aplicación.
 */
object RouteMapper {
    private val DATE_FMT = DateTimeFormatter.ISO_LOCAL_DATE

    /**
     * Convierte una entidad RouteWithPoints a un modelo de dominio Route.
     * @return Un objeto Route con todos los datos de la entidad
     */
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

    /**
     * Convierte un modelo de dominio Route a entidades para la base de datos.
     * @return Un par con la entidad ruta y la lista de entidades punto asociadas
     */
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

    /**
     * Convierte una entidad PointEntity a un modelo de dominio Point.
     * @return Un objeto Point con los datos de la entidad
     */
    fun PointEntity.toDomain(): Point {
        return Point(
            latitude = latitude,
            longitude = longitude,
            altitude = altitude,
            timestamp = timestamp
        )
    }
    
    /**
     * Método auxiliar para convertir desde entidad a modelo de dominio.
     * @param rwp La entidad RouteWithPoints a convertir
     * @return Un objeto Route con todos los datos de la entidad
     */
    fun fromEntity(rwp: RouteWithPoints): Route = rwp.toDomain()
    
    /**
     * Método auxiliar para convertir desde modelo de dominio a entidades.
     * @param route El modelo de dominio Route a convertir
     * @return Un par con la entidad ruta y la lista de entidades punto asociadas
     */
    fun toEntity(route: Route): Pair<RouteEntity, List<PointEntity>> = route.toEntities()
}
