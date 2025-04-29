package com.example.stepbystep.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "routes")
data class RouteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val date: String,
    val distance: Double,
    val duration: Long,
    val elevation: Double = 0.0,
    val elevationGain: Double = 0.0,
    val imagePath: String? = null  // Nueva columna para la ruta de la imagen
)
