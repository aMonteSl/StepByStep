package com.example.stepbystep.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.stepbystep.data.local.RouteDao
import com.example.stepbystep.data.local.RouteEntity
import com.example.stepbystep.data.local.PointEntity
import com.example.stepbystep.data.mapper.toDomain
import com.example.stepbystep.data.mapper.toEntities
import com.example.stepbystep.data.mapper.toRouteEntity
import com.example.stepbystep.domain.model.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RouteRepository(private val dao: RouteDao) {

    // Transform database entities to domain models using the mapper extensions
    val routes: LiveData<List<Route>> = dao.getAllRoutesWithPoints().map { routesWithPoints ->
        routesWithPoints.map { it.toDomain() }
    }

    suspend fun addRoute(route: Route) {
        withContext(Dispatchers.IO) {
            val (routeEntity, pointEntities) = route.toEntities()
            val routeId = dao.insert(routeEntity)
            
            // Update point entities with the new route ID
            val updatedPoints = pointEntities.map { it.copy(routeId = routeId) }
            dao.insertAll(updatedPoints)
        }
    }

    suspend fun updateRoute(route: Route) {
        withContext(Dispatchers.IO) {
            val (routeEntity, pointEntities) = route.toEntities()
            dao.update(routeEntity)
            
            // First delete existing points
            dao.deletePointsForRoute(routeEntity.id)
            
            // Then insert the updated points
            dao.insertAll(pointEntities)
        }
    }

    suspend fun deleteRoute(route: Route) {
        withContext(Dispatchers.IO) {
            // Be explicit about which delete method we're calling to avoid ambiguity
            dao.delete(route.toRouteEntity())
            // Points will be deleted automatically due to foreign key constraints
        }
    }

    suspend fun clearAllRoutes() {
        withContext(Dispatchers.IO) {
            dao.deleteAllRoutes()
        }
    }

    suspend fun getRouteById(routeId: Long): Route {
        return withContext(Dispatchers.IO) {
            val routeWithPoints = dao.getRouteWithPointsById(routeId)
                ?: throw IllegalArgumentException("No route found with ID: $routeId")
            
            routeWithPoints.toDomain()
        }
    }
}
