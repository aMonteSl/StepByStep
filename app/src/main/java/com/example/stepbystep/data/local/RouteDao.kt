package com.example.stepbystep.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

data class RouteWithPoints(
    @Embedded val route: RouteEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "routeId"
    )
    val points: List<PointEntity>
)

@Dao
interface RouteDao {
    // Basic CRUD operations for Routes
    @Insert
    suspend fun insert(route: RouteEntity): Long

    @Update
    suspend fun update(route: RouteEntity)

    @Delete
    suspend fun delete(route: RouteEntity)

    // Basic CRUD operations for Points
    @Insert
    suspend fun insert(point: PointEntity)

    @Insert
    suspend fun insertAll(points: List<PointEntity>)

    @Delete
    suspend fun delete(point: PointEntity)

    // Query operations
    @Transaction
    @Query("SELECT * FROM routes")
    fun getAllRoutesWithPoints(): LiveData<List<RouteWithPoints>>

    @Query("SELECT * FROM routes WHERE id = :routeId")
    suspend fun getRouteById(routeId: Long): RouteEntity?

    @Query("SELECT * FROM points WHERE routeId = :routeId")
    suspend fun getPointsForRoute(routeId: Long): List<PointEntity>

    @Transaction
    @Query("SELECT * FROM routes WHERE id = :routeId")
    suspend fun getRouteWithPointsById(routeId: Long): RouteWithPoints?

    // Delete operations
    @Query("DELETE FROM routes")
    suspend fun deleteAllRoutes()

    @Query("DELETE FROM points WHERE routeId = :routeId")
    suspend fun deletePointsForRoute(routeId: Long)
}
