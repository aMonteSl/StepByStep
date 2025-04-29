package com.example.stepbystep.ui.routedetail

import android.content.Context
import androidx.lifecycle.*
import com.example.stepbystep.data.local.RouteRoomDatabase
import com.example.stepbystep.data.repository.RouteRepository
import com.example.stepbystep.domain.model.Route
import com.example.stepbystep.domain.model.Point
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

class RouteDetailViewModel(private val context: Context) : ViewModel() {
    
    private val repository: RouteRepository
    
    init {
        val database = RouteRoomDatabase.getInstance(context)
        repository = RouteRepository(database.routeDao())
    }

    private val _route = MutableLiveData<Route>()
    val route: LiveData<Route> = _route

    private val _chartData = MutableLiveData<List<Pair<Float, Float>>>()
    val chartData: LiveData<List<Pair<Float, Float>>> = _chartData

    fun loadRoute(routeId: Long) {
        viewModelScope.launch {
            val loadedRoute = repository.getRouteById(routeId)
            _route.value = loadedRoute
            
            // Prepare chart data
            prepareChartData(loadedRoute.points)
        }
    }

    private fun prepareChartData(points: List<Point>) {
        if (points.isEmpty()) {
            _chartData.value = emptyList()
            return
        }

        // Create data points for the chart where x is distance (accumulated) and y is altitude
        val chartPoints = mutableListOf<Pair<Float, Float>>()
        var accumulatedDistance = 0f
        
        chartPoints.add(Pair(0f, points.first().altitude.toFloat()))
        
        for (i in 1 until points.size) {
            val current = points[i]
            val previous = points[i-1]
            
            // Calculate distance between consecutive points
            val distance = calculateDistance(
                previous.latitude, previous.longitude,
                current.latitude, current.longitude
            )
            
            accumulatedDistance += distance
            chartPoints.add(Pair(accumulatedDistance, current.altitude.toFloat()))
        }
        
        _chartData.value = chartPoints
    }
    
    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        // Simple distance calculation, could be replaced with more accurate formula
        val earthRadius = 6371000.0 // meters
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return (earthRadius * c).toFloat()
    }

    suspend fun exportGpx(): File = withContext(Dispatchers.IO) {
        val route = route.value ?: throw IllegalStateException("No route loaded")
        
        // Create a temporary file for the GPX data
        val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val timestamp = dateFormat.format(Date())
        val filename = "route_${route.id}_$timestamp.gpx"
        val file = File(context.cacheDir, filename)
        
        // Generate GPX content
        FileWriter(file).use { writer ->
            writer.append("""
                <?xml version="1.0" encoding="UTF-8"?>
                <gpx version="1.1" creator="StepByStep App"
                  xmlns="http://www.topografix.com/GPX/1/1"
                  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xsi:schemaLocation="http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd">
                  <metadata>
                    <name>${route.name}</name>
                    <desc>${route.description}</desc>
                    <time>${route.date}</time>
                  </metadata>
                  <trk>
                    <name>${route.name}</name>
                    <desc>${route.description}</desc>
                    <trkseg>
            """.trimIndent())
            
            // Add track points
            for (point in route.points) {
                val timeStr = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                    .format(Date(point.timestamp))
                
                writer.append("""
                      <trkpt lat="${point.latitude}" lon="${point.longitude}">
                        <ele>${point.altitude}</ele>
                        <time>$timeStr</time>
                      </trkpt>
                """.trimIndent())
            }
            
            writer.append("""
                    </trkseg>
                  </trk>
                </gpx>
            """.trimIndent())
        }
        
        return@withContext file
    }
}