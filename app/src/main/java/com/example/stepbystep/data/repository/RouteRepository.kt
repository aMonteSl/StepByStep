package com.example.stepbystep.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.stepbystep.data.local.RouteDao
import com.example.stepbystep.data.mapper.RouteMapper
import com.example.stepbystep.domain.model.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log

/**
 * Repositorio que actúa como capa intermedia entre la fuente de datos (DAO)
 * y las capas superiores de la aplicación. Proporciona métodos para realizar
 * operaciones con rutas, abstrayendo la lógica de acceso a datos.
 */
class RouteRepository(private val dao: RouteDao) {

    /**
     * Obtiene todas las rutas almacenadas en la base de datos.
     * Transforma automáticamente las entidades de base de datos en modelos de dominio.
     */
    val routes: LiveData<List<Route>> = dao.getAllRoutesWithPoints().map { routesWithPoints ->
        routesWithPoints.map { RouteMapper.fromEntity(it) }
    }

    /**
     * Añade una nueva ruta a la base de datos.
     * @param route La ruta de dominio a guardar
     */
    suspend fun addRoute(route: Route) {
        withContext(Dispatchers.IO) {
            Log.d("RouteRepository", "Adding route with imagePath: ${route.imagePath}")
            
            val (routeEntity, pointEntities) = RouteMapper.toEntity(route)
            
            Log.d("RouteRepository", "RouteEntity has imagePath: ${routeEntity.imagePath}")
            
            // Primero insertamos la ruta para obtener su ID generado
            val routeId = dao.insert(routeEntity)
            
            // Actualizamos los puntos con el ID de ruta recién generado
            val updatedPoints = pointEntities.map { it.copy(routeId = routeId) }
            dao.insertAll(updatedPoints)
        }
    }

    /**
     * Actualiza una ruta existente en la base de datos.
     * @param route La ruta con los datos actualizados
     */
    suspend fun updateRoute(route: Route) {
        withContext(Dispatchers.IO) {
            val (routeEntity, pointEntities) = RouteMapper.toEntity(route)
            dao.update(routeEntity)
            
            // Primero eliminamos los puntos existentes
            dao.deletePointsForRoute(routeEntity.id)
            
            // Luego insertamos los puntos actualizados
            dao.insertAll(pointEntities)
        }
    }

    /**
     * Elimina una ruta de la base de datos junto con todos sus puntos.
     * @param route La ruta a eliminar
     */
    suspend fun deleteRoute(route: Route) {
        withContext(Dispatchers.IO) {
            // Convertir a entidad y eliminar
            val (routeEntity, _) = RouteMapper.toEntity(route)
            dao.delete(routeEntity)
            // Los puntos se eliminarán automáticamente debido a las restricciones de clave foránea
        }
    }

    /**
     * Elimina todas las rutas de la base de datos.
     */
    suspend fun clearAllRoutes() {
        withContext(Dispatchers.IO) {
            dao.deleteAllRoutes()
        }
    }

    /**
     * Obtiene una ruta específica por su ID.
     * @param routeId El ID de la ruta a buscar
     * @return La ruta encontrada convertida a modelo de dominio
     * @throws IllegalArgumentException Si no se encuentra la ruta con el ID especificado
     */
    suspend fun getRouteById(routeId: Long): Route {
        return withContext(Dispatchers.IO) {
            val routeWithPoints = dao.getRouteWithPointsById(routeId)
                ?: throw IllegalArgumentException("No se encontró ninguna ruta con ID: $routeId")
            
            RouteMapper.fromEntity(routeWithPoints)
        }
    }
}
