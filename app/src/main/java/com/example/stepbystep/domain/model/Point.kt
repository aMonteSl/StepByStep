package com.example.stepbystep.domain.model

/**
 * Modelo de dominio que representa un punto geográfico específico en una ruta.
 * Almacena las coordenadas, altitud y el momento exacto en que se registró el punto.
 * Se utiliza para reconstruir y visualizar el trazado de la ruta en el mapa.
 */
data class Point(
    val latitude: Double,   // Latitud en grados decimales (coordenada norte-sur)
    val longitude: Double,  // Longitud en grados decimales (coordenada este-oeste)
    val altitude: Double,   // Elevación en metros sobre el nivel del mar
    val timestamp: Long     // Marca temporal en milisegundos desde epoch (1/1/1970)
)