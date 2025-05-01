package com.example.stepbystep.util

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import org.xml.sax.InputSource
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Clase que representa los datos extraídos de un archivo GPX.
 * 
 * @property name Nombre de la ruta
 * @property description Descripción de la ruta
 * @property points Lista de puntos geográficos con elevación y timestamp
 */
data class GpxData(
    val name: String,
    val description: String,
    val points: List<GpxPoint>
)

/**
 * Clase que representa un punto geográfico en un archivo GPX.
 * 
 * @property latitude Latitud en grados decimales
 * @property longitude Longitud en grados decimales
 * @property elevation Elevación en metros
 * @property timestamp Marca de tiempo en milisegundos
 */
data class GpxPoint(
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
    val timestamp: Long
)

/**
 * Utilidad para analizar (parsear) archivos GPX e importar rutas.
 * 
 * GPX (GPS Exchange Format) es un formato XML para intercambiar datos GPS
 * entre aplicaciones. Esta clase extrae puntos de ruta, elevación,
 * y metadatos como nombre y descripción de los archivos GPX.
 */
object GpxParser {
    
    /**
     * Analiza un archivo GPX a partir de un URI.
     * 
     * @param context Contexto para acceder al contentResolver
     * @param uri URI del archivo GPX a analizar
     * @return Datos extraídos del GPX o null si ocurre un error
     */
    fun parse(context: Context, uri: Uri): GpxData? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            return parse(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
    
    /**
     * Analiza un archivo GPX a partir de un flujo de entrada (InputStream).
     * 
     * @param inputStream Flujo de entrada del archivo GPX
     * @return Datos extraídos del GPX
     */
    private fun parse(inputStream: InputStream): GpxData {
        inputStream.use { stream ->
            val docBuilderFactory = DocumentBuilderFactory.newInstance()
            // Esta línea es clave - habilitar el soporte de namespace
            docBuilderFactory.isNamespaceAware = true
            val docBuilder = docBuilderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(InputSource(stream))
            doc.documentElement.normalize()
            
            // Extraer nombre y descripción del metadata
            val metadata = doc.getElementsByTagName("metadata").item(0) as? Element
            var name = "Ruta importada"
            var description = ""
            
            if (metadata != null) {
                // Busca el nombre en metadata usando el método correcto para manejar namespaces
                val nameNodes = metadata.getElementsByTagName("name")
                if (nameNodes.length > 0) {
                    name = nameNodes.item(0).textContent.trim()
                    // Añadir log para debug
                    Log.d("GpxParser", "Found name in metadata: $name")
                }
                
                val descNodes = metadata.getElementsByTagName("desc")
                if (descNodes.length > 0) {
                    description = descNodes.item(0).textContent.trim()
                    Log.d("GpxParser", "Found description in metadata: $description")
                }
            }
            
            // Si nombre/descripción no está en metadata, intentar buscar en track
            if (name == "Ruta importada") {
                val trkElements = doc.getElementsByTagName("trk")
                if (trkElements.length > 0) {
                    val trk = trkElements.item(0) as Element
                    val trkNameNodes = trk.getElementsByTagName("name")
                    if (trkNameNodes.length > 0) {
                        name = trkNameNodes.item(0).textContent.trim()
                        Log.d("GpxParser", "Found name in track: $name")
                    }
                    
                    // Intentar obtener descripción del track si no se encontró antes
                    if (description.isEmpty()) {
                        val trkDescNodes = trk.getElementsByTagName("desc")
                        if (trkDescNodes.length > 0) {
                            description = trkDescNodes.item(0).textContent.trim()
                            Log.d("GpxParser", "Found description in track: $description")
                        }
                    }
                }
            }
            
            // Obtener puntos de la ruta
            val trackPoints = doc.getElementsByTagName("trkpt")
            val points = parseTrackPoints(trackPoints)
            
            return GpxData(name, description, points)
        }
    }
    
    /**
     * Analiza la lista de nodos de puntos de ruta para extraer
     * coordenadas, elevación y timestamps.
     * 
     * @param trackPoints Lista de nodos XML con puntos de ruta
     * @return Lista de puntos geográficos procesados
     */
    private fun parseTrackPoints(trackPoints: NodeList): List<GpxPoint> {
        val points = mutableListOf<GpxPoint>()
        
        for (i in 0 until trackPoints.length) {
            val trkpt = trackPoints.item(i) as Element
            val lat = trkpt.getAttribute("lat").toDouble()
            val lon = trkpt.getAttribute("lon").toDouble()
            
            val eleNodes = trkpt.getElementsByTagName("ele")
            var elevation = 0.0
            if (eleNodes.length > 0) {
                elevation = eleNodes.item(0).textContent.toDouble()
            }
            
            val timeNodes = trkpt.getElementsByTagName("time")
            var timestamp = System.currentTimeMillis()
            if (timeNodes.length > 0) {
                // Analizar formato de tiempo ISO 8601, simplificando para este ejemplo
                val timeText = timeNodes.item(0).textContent
                timestamp = parseTimeToMillis(timeText)
            }
            
            points.add(GpxPoint(lat, lon, elevation, timestamp))
        }
        
        return points
    }
    
    /**
     * Convierte una cadena de tiempo en formato ISO 8601 a milisegundos.
     * 
     * @param timeText Tiempo en formato "yyyy-MM-dd'T'HH:mm:ssZ"
     * @return Tiempo en milisegundos desde epoch
     */
    private fun parseTimeToMillis(timeText: String): Long {
        // Análisis simplificado de timestamp - en una app real, usar análisis de fecha apropiado
        return try {
            // Eliminar sufijo Z y analizar como formato ISO
            val time = timeText.replace("Z", "")
            val format = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.US)
            format.parse(time)?.time ?: System.currentTimeMillis()
        } catch (e: Exception) {
            System.currentTimeMillis()
        }
    }
    
    /**
     * Calcula estadísticas de la ruta: distancia total, ganancia de elevación,
     * y elevación media.
     * 
     * @param points Lista de puntos geográficos de la ruta
     * @return Triple con (distancia en km, ganancia de elevación en m, elevación media en m)
     */
    fun calculateRouteStats(points: List<GpxPoint>): Triple<Double, Double, Double> {
        if (points.isEmpty()) return Triple(0.0, 0.0, 0.0)
        
        var distance = 0.0
        var elevationGain = 0.0
        
        for (i in 1 until points.size) {
            val prev = points[i-1]
            val curr = points[i]
            
            // Calcular distancia entre puntos consecutivos usando utilidad de Google Maps
            val prevLatLng = LatLng(prev.latitude, prev.longitude)
            val currLatLng = LatLng(curr.latitude, curr.longitude)
            distance += com.google.maps.android.SphericalUtil.computeDistanceBetween(
                prevLatLng, currLatLng) / 1000 // Convertir a km
            
            // Calcular ganancia de elevación (solo cambios positivos)
            val elevDiff = curr.elevation - prev.elevation
            if (elevDiff > 0) {
                elevationGain += elevDiff
            }
        }
        
        // La elevación media es simplemente el promedio de todas las elevaciones de puntos
        val averageElevation = points.map { it.elevation }.average()
        
        return Triple(distance, elevationGain, averageElevation)
    }
}