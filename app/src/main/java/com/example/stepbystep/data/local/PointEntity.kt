package com.example.stepbystep.data.local

import androidx.room.*
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "points",
    foreignKeys = [
        ForeignKey(
            entity = RouteEntity::class,
            parentColumns = ["id"],
            childColumns  = ["routeId"],
            onDelete      = ForeignKey.CASCADE
        )
    ],
    indices = [Index("routeId")]
)
data class PointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val routeId: Long,      // FK a RouteEntity

    val latitude: Double,
    val longitude: Double,
    val altitude: Double,   // en m
    val timestamp: Long     // System.currentTimeMillis()
)
