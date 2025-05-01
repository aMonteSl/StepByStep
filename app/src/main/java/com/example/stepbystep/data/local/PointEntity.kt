package com.example.stepbystep.data.local

import androidx.room.*
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa un punto geográfico en una ruta.
 * Cada punto tiene una posición (latitud, longitud), altitud y marca de tiempo.
 * Está asociado a una ruta específica mediante la clave foránea routeId.
 */
@Entity(
    tableName = "points",  // Nombre de la tabla en la base de datos SQLite
    foreignKeys = [
        ForeignKey(
            entity = RouteEntity::class,  // Entidad padre (la ruta a la que pertenece)
            parentColumns = ["id"],       // Columna de referencia en la tabla padre
            childColumns  = ["routeId"],  // Columna en esta tabla que hace referencia al padre
            onDelete      = ForeignKey.CASCADE  // Si se elimina la ruta, se eliminan todos sus puntos
        )
    ],
    indices = [Index("routeId")]  // Índice para mejorar el rendimiento en consultas por routeId
)
data class PointEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,      // Identificador único del punto, generado automáticamente
    
    val routeId: Long,      // Clave foránea que vincula este punto con su ruta correspondiente
    
    val latitude: Double,   // Coordenada de latitud del punto geográfico
    val longitude: Double,  // Coordenada de longitud del punto geográfico
    val altitude: Double,   // Elevación en metros sobre el nivel del mar
    val timestamp: Long     // Marca temporal en milisegundos (System.currentTimeMillis())
)
