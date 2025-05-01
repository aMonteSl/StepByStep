package com.example.stepbystep.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Clase que relaciona una ruta con todos sus puntos.
 * Utiliza las anotaciones @Embedded y @Relation de Room para definir
 * la relación uno-a-muchos entre rutas y puntos.
 */
data class RouteWithPoints(
    @Embedded val route: RouteEntity,  // La ruta principal
    @Relation(
        parentColumn = "id",          // Columna de la ruta (padre)
        entityColumn = "routeId"      // Columna del punto (hijo) que hace referencia al padre
    )
    val points: List<PointEntity>     // Lista de puntos pertenecientes a esta ruta
)

/**
 * Interfaz de acceso a datos (DAO) para las operaciones de base de datos
 * relacionadas con rutas y puntos. Define métodos CRUD y consultas específicas.
 */
@Dao
interface RouteDao {
    // Operaciones CRUD básicas para Rutas
    
    /**
     * Inserta una nueva ruta en la base de datos.
     * @param route La entidad ruta a insertar
     * @return El ID generado para la nueva ruta insertada
     */
    @Insert
    suspend fun insert(route: RouteEntity): Long

    /**
     * Actualiza una ruta existente en la base de datos.
     * @param route La entidad ruta con los nuevos datos
     */
    @Update
    suspend fun update(route: RouteEntity)

    /**
     * Elimina una ruta de la base de datos.
     * @param route La entidad ruta a eliminar
     */
    @Delete
    suspend fun delete(route: RouteEntity)

    // Operaciones CRUD básicas para Puntos
    
    /**
     * Inserta un nuevo punto en la base de datos.
     * @param point La entidad punto a insertar
     */
    @Insert
    suspend fun insert(point: PointEntity)

    /**
     * Inserta múltiples puntos en la base de datos.
     * @param points Lista de entidades punto a insertar
     */
    @Insert
    suspend fun insertAll(points: List<PointEntity>)

    /**
     * Elimina un punto de la base de datos.
     * @param point La entidad punto a eliminar
     */
    @Delete
    suspend fun delete(point: PointEntity)

    // Operaciones de consulta
    
    /**
     * Obtiene todas las rutas junto con sus puntos.
     * @return LiveData con la lista de rutas y sus puntos
     */
    @Transaction
    @Query("SELECT * FROM routes")
    fun getAllRoutesWithPoints(): LiveData<List<RouteWithPoints>>

    /**
     * Obtiene una ruta por su ID.
     * @param routeId El ID de la ruta a buscar
     * @return La entidad ruta encontrada o null si no existe
     */
    @Query("SELECT * FROM routes WHERE id = :routeId")
    suspend fun getRouteById(routeId: Long): RouteEntity?

    /**
     * Obtiene todos los puntos asociados a una ruta.
     * @param routeId El ID de la ruta cuyos puntos se desean obtener
     * @return Lista de entidades punto asociadas a la ruta
     */
    @Query("SELECT * FROM points WHERE routeId = :routeId")
    suspend fun getPointsForRoute(routeId: Long): List<PointEntity>

    /**
     * Obtiene una ruta junto con sus puntos por su ID.
     * @param routeId El ID de la ruta a buscar
     * @return La entidad RouteWithPoints encontrada o null si no existe
     */
    @Transaction
    @Query("SELECT * FROM routes WHERE id = :routeId")
    suspend fun getRouteWithPointsById(routeId: Long): RouteWithPoints?

    // Operaciones de eliminación
    
    /**
     * Elimina todas las rutas de la base de datos.
     */
    @Query("DELETE FROM routes")
    suspend fun deleteAllRoutes()

    /**
     * Elimina todos los puntos asociados a una ruta.
     * @param routeId El ID de la ruta cuyos puntos se desean eliminar
     */
    @Query("DELETE FROM points WHERE routeId = :routeId")
    suspend fun deletePointsForRoute(routeId: Long)
}
