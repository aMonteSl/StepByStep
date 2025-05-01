package com.example.stepbystep.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.stepbystep.data.local.RouteDao
import com.example.stepbystep.data.mapper.RouteMapper
import com.example.stepbystep.domain.model.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log

class RouteRepository(private val dao: RouteDao) {

    // Transform database entities to domain models using the mapper from RouteMapper object
    val routes: LiveData<List<Route>> = dao.getAllRoutesWithPoints().map { routesWithPoints ->
        routesWithPoints.map { RouteMapper.fromEntity(it) }
    }

    suspend fun addRoute(route: Route) {
        withContext(Dispatchers.IO) {
            Log.d("RouteRepository", "Adding route with imagePath: ${route.imagePath}")
            
            val (routeEntity, pointEntities) = RouteMapper.toEntity(route)
            
            Log.d("RouteRepository", "RouteEntity has imagePath: ${routeEntity.imagePath}")
            
            val routeId = dao.insert(routeEntity)
            
            // Update point entities with the new route ID
            val updatedPoints = pointEntities.map { it.copy(routeId = routeId) }
            dao.insertAll(updatedPoints)
        }
    }

    suspend fun updateRoute(route: Route) {
        withContext(Dispatchers.IO) {
            val (routeEntity, pointEntities) = RouteMapper.toEntity(route)
            dao.update(routeEntity)
            
            // First delete existing points
            dao.deletePointsForRoute(routeEntity.id)
            
            // Then insert the updated points
            dao.insertAll(pointEntities)
        }
    }

    suspend fun deleteRoute(route: Route) {
        withContext(Dispatchers.IO) {
            // Convert to RouteEntity and delete
            val (routeEntity, _) = RouteMapper.toEntity(route)
            dao.delete(routeEntity)
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
            
            RouteMapper.fromEntity(routeWithPoints)
        }
    }
}
