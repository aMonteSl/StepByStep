package com.example.stepbystep.domain.model

import java.time.LocalDate

/**
 * Modelo de dominio que representa una ruta completa realizada por el usuario.
 * Contiene todos los datos relevantes de la ruta como nombre, descripción, estadísticas
 * y la colección de puntos que conforman el trazado geográfico.
 * 
 * Este es el modelo principal utilizado en la lógica de negocio de la aplicación,
 * independiente de la forma en que se almacene en la base de datos.
 */
data class Route(
    val id: Long = 0,               // Identificador único de la ruta
    val name: String,               // Nombre descriptivo de la ruta
    val description: String = "",   // Descripción detallada opcional
    val date: String,               // Fecha en formato texto ISO (yyyy-MM-dd)
    val distance: Double,           // Distancia total en kilómetros
    val duration: Long,             // Duración total en milisegundos
    val elevation: Double = 0.0,    // Elevación máxima alcanzada en metros
    val elevationGain: Double = 0.0,// Ganancia de elevación acumulada en metros 
    val imagePath: String? = null,  // Ruta al archivo de imagen asociado (opcional)
    val points: List<Point> = emptyList() // Colección de puntos que conforman la ruta
) {
    // Propiedades calculadas para facilitar conversiones y acceso a datos
    val distanceMeters: Double get() = distance * 1000  // Distancia convertida a metros
    val distanceKm: Double get() = distance             // Distancia en kilómetros (alias para claridad)
    val durationMs: Long get() = duration               // Duración en milisegundos (alias para claridad)
    val elevationM: Double get() = elevation            // Elevación en metros (alias para claridad)
}
