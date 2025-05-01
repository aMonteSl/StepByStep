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

data class GpxData(
    val name: String,
    val description: String,
    val points: List<GpxPoint>
)

data class GpxPoint(
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
    val timestamp: Long
)

object GpxParser {
    
    fun parse(context: Context, uri: Uri): GpxData? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
            return parse(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
    
    private fun parse(inputStream: InputStream): GpxData {
        inputStream.use { stream ->
            val docBuilderFactory = DocumentBuilderFactory.newInstance()
            // Esta línea es clave - habilitar el soporte de namespace
            docBuilderFactory.isNamespaceAware = true
            val docBuilder = docBuilderFactory.newDocumentBuilder()
            val doc = docBuilder.parse(InputSource(stream))
            doc.documentElement.normalize()
            
            // Extract name and desc from metadata
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
            
            // If name/desc not in metadata, try track
            if (name == "Ruta importada") {
                val trkElements = doc.getElementsByTagName("trk")
                if (trkElements.length > 0) {
                    val trk = trkElements.item(0) as Element
                    val trkNameNodes = trk.getElementsByTagName("name")
                    if (trkNameNodes.length > 0) {
                        name = trkNameNodes.item(0).textContent.trim()
                        Log.d("GpxParser", "Found name in track: $name")
                    }
                    
                    // Try to get description from track if not found earlier
                    if (description.isEmpty()) {
                        val trkDescNodes = trk.getElementsByTagName("desc")
                        if (trkDescNodes.length > 0) {
                            description = trkDescNodes.item(0).textContent.trim()
                            Log.d("GpxParser", "Found description in track: $description")
                        }
                    }
                }
            }
            
            // Get track points
            val trackPoints = doc.getElementsByTagName("trkpt")
            val points = parseTrackPoints(trackPoints)
            
            return GpxData(name, description, points)
        }
    }
    
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
                // Parse ISO 8601 time format, simplifying for this example
                val timeText = timeNodes.item(0).textContent
                timestamp = parseTimeToMillis(timeText)
            }
            
            points.add(GpxPoint(lat, lon, elevation, timestamp))
        }
        
        return points
    }
    
    private fun parseTimeToMillis(timeText: String): Long {
        // Simplified timestamp parsing - in a real app, use proper date parsing
        return try {
            // Remove Z suffix and parse as ISO format
            val time = timeText.replace("Z", "")
            val format = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.US)
            format.parse(time)?.time ?: System.currentTimeMillis()
        } catch (e: Exception) {
            System.currentTimeMillis()
        }
    }
    
    // Helper function to calculate distance, elevation gain, etc.
    fun calculateRouteStats(points: List<GpxPoint>): Triple<Double, Double, Double> {
        if (points.isEmpty()) return Triple(0.0, 0.0, 0.0)
        
        var distance = 0.0
        var elevationGain = 0.0
        
        for (i in 1 until points.size) {
            val prev = points[i-1]
            val curr = points[i]
            
            // Calculate distance between consecutive points using Google Maps utility
            val prevLatLng = LatLng(prev.latitude, prev.longitude)
            val currLatLng = LatLng(curr.latitude, curr.longitude)
            distance += com.google.maps.android.SphericalUtil.computeDistanceBetween(
                prevLatLng, currLatLng) / 1000 // Convert to km
            
            // Calculate elevation gain (only positive changes)
            val elevDiff = curr.elevation - prev.elevation
            if (elevDiff > 0) {
                elevationGain += elevDiff
            }
        }
        
        // Average elevation is just the average of all point elevations
        val averageElevation = points.map { it.elevation }.average()
        
        return Triple(distance, elevationGain, averageElevation)
    }
}