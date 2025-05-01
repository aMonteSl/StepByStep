package com.example.stepbystep.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa una ruta completa en la aplicación.
 * Almacena los datos principales de la ruta como nombre, descripción, distancia, etc.
 * Cada ruta puede tener múltiples puntos asociados en la tabla points.
 */
@Entity(tableName = "routes") // Nombre de la tabla en la base de datos SQLite
data class RouteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,               // Identificador único de la ruta, generado automáticamente
    val name: String,               // Nombre de la ruta proporcionado por el usuario
    val description: String,        // Descripción detallada opcional
    val date: String,               // Fecha en la que se realizó la ruta (formato texto)
    val distance: Double,           // Distancia total recorrida en kilómetros
    val duration: Long,             // Duración total en milisegundos
    val elevation: Double = 0.0,    // Elevación máxima alcanzada en metros
    val elevationGain: Double = 0.0, // Ganancia de elevación acumulada en metros
    val imagePath: String? = null   // Ruta al archivo de imagen asociado (opcional)
)
