package com.example.stepbystep.ui.routedetail

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import com.example.stepbystep.R
import com.example.stepbystep.databinding.ActivityRouteDetailBinding
import com.example.stepbystep.util.MapUtils.configureMapStyle
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.launch
import java.io.File

class RouteDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ROUTE_ID = "extra_route_id"
    }

    private lateinit var binding: ActivityRouteDetailBinding
    private val viewModel: RouteDetailViewModel by viewModels {
        RouteDetailViewModelFactory(this)
    }
    
    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRouteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        
        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        
        // Setup map
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        
        // Get route ID from intent
        val routeId = intent.getLongExtra(EXTRA_ROUTE_ID, -1)
        if (routeId == -1L) {
            Toast.makeText(this, "Error: No se pudo cargar la ruta", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        // Load route data
        viewModel.loadRoute(routeId)
        
        // Setup observers
        viewModel.route.observe(this) { route ->
            title = route.name
            setupMap(route.points.map { LatLng(it.latitude, it.longitude) })
            
            // Cargar la imagen si existe
            route.imagePath?.let { path ->
                try {
                    val imageFile = File(path)
                    if (imageFile.exists()) {
                        Log.d("RouteDetail", "Image file exists at path: $path")
                        binding.routeImage.setImageURI(Uri.fromFile(imageFile))
                        binding.routeImageCard.visibility = View.VISIBLE
                    } else {
                        Log.e("RouteDetail", "Image file doesn't exist at path: $path")
                        binding.routeImageCard.visibility = View.GONE
                    }
                } catch (e: Exception) {
                    Log.e("RouteDetail", "Error loading image: ${e.message}", e)
                    binding.routeImageCard.visibility = View.GONE
                }
            } ?: run {
                Log.d("RouteDetail", "No image path available for this route")
                binding.routeImageCard.visibility = View.GONE
            }
        }
        
        viewModel.chartData.observe(this) { chartData ->
            setupChart(chartData)
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.route_detail_menu, menu)
        return true
    }
    
    private fun setupMap(points: List<LatLng>) {
        mapView.getMapAsync { map ->
            googleMap = map
            
            // Usar el parámetro map (que sabemos que no es nulo) en lugar de la propiedad googleMap
            map.configureMapStyle(this)
            
            if (points.isNotEmpty()) {
                // Draw route on map
                map.addPolyline(
                    PolylineOptions()
                        .addAll(points)
                        .width(8f)
                        .color(getColor(R.color.turquoise))
                )
                
                // Zoom to fit the entire route
                val boundsBuilder = LatLngBounds.Builder()
                points.forEach { boundsBuilder.include(it) }
                
                // Add padding to the bounds
                val bounds = boundsBuilder.build()
                val padding = resources.getDimensionPixelSize(R.dimen.map_padding)
                map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
            }
        }
    }
    
    private fun setupChart(chartData: List<Pair<Float, Float>>) {
        val chart = binding.chartElevation
        
        // Si no hay datos o solo un punto, no hay nada que graficar
        if (chartData.isEmpty() || chartData.size == 1) {
            chart.setNoDataText("No hay datos de elevación disponibles")
            return
        }
        
        // Convert data to entries
        val entries = chartData.map { (distance, altitude) ->
            Entry(distance / 1000f, altitude) // Convert distance to km for x-axis
        }
        
        // Calcular la distancia total para determinar la unidad a usar
        val totalDistanceKm = if (entries.isNotEmpty()) entries.last().x else 0f
        val useMeters = totalDistanceKm < 0.5f // Si menos de 500m, mostrar en metros
        
        // Determinar si estamos en modo oscuro o claro
        val isNightMode = (resources.configuration.uiMode and 
                          Configuration.UI_MODE_NIGHT_MASK) == 
                          Configuration.UI_MODE_NIGHT_YES
        
        // Seleccionar los colores según el modo
        val lineColor = if (isNightMode) {
            getColor(R.color.turquoise)
        } else {
            getColor(R.color.oxford)
        }
        
        val fillColor = if (isNightMode) {
            getColor(R.color.turquoise_light)
        } else {
            getColor(R.color.oxford_light)
        }
        
        // Color con alpha para la cuadrícula (30% de opacidad)
        var gridColor = Color.argb(76, // 30% de 255 es ~76
                                  Color.red(lineColor),
                                  Color.green(lineColor),
                                  Color.blue(lineColor))
        
        // Recrear las entries si necesitamos mostrar en metros
        val adjustedEntries = if (useMeters) {
            chartData.map { (distance, altitude) ->
                Entry(distance, altitude) // Mantener en metros para el eje X
            }
        } else {
            entries // Mantener en kilómetros como ya se calculó
        }
        
        val dataSet = LineDataSet(adjustedEntries, "Elevación").apply {
            color = lineColor
            valueTextColor = lineColor
            lineWidth = 2f
            setDrawCircles(false)
            setDrawValues(false)
            setDrawFilled(true)
            setFillColor(fillColor)
            fillAlpha = 60 // Semitransparente
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }
        
        // Configure chart
        chart.apply {
            data = LineData(dataSet)
            description.isEnabled = false
            legend.isEnabled = false
            setTouchEnabled(true)
            setScaleEnabled(true)
            setPinchZoom(true)
            
            // Configure X axis (distance in km or m)
            xAxis.apply {
                textColor = lineColor
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(true)
                axisLineColor = lineColor
                gridColor = gridColor
                gridLineWidth = 0.5f
                
                // Formatear valores según la unidad seleccionada
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return if (useMeters) {
                            // Mostrar en metros sin decimales
                            "${value.toInt()} m"
                        } else {
                            // Si la distancia es pequeña pero no tanto como para usar metros, usar 2 decimales
                            if (totalDistanceKm < 2f) {
                                String.format("%.2f km", value)
                            } else {
                                // Para distancias mayores, usar formato entero o 1 decimal
                                "${value.toInt()} km"
                            }
                        }
                    }
                }
            }
            
            // Configure Y axis (elevation in m)
            axisLeft.apply {
                textColor = lineColor
                axisLineColor = lineColor
                gridColor = gridColor
                gridLineWidth = 0.5f
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return "${value.toInt()} m"
                    }
                }
            }
            
            axisRight.isEnabled = false
            
            // Refresh
            invalidate()
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menu_export_gpx -> {
                exportGpx()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun exportGpx() {
        lifecycleScope.launch {
            try {
                val gpxFile = viewModel.exportGpx()
                
                // Create a content URI for the file using FileProvider
                val contentUri = FileProvider.getUriForFile(
                    this@RouteDetailActivity,
                    "${applicationContext.packageName}.fileprovider",
                    gpxFile
                )
                
                // Create an intent to share the file
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_STREAM, contentUri)
                    type = "application/gpx+xml"
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                
                startActivity(Intent.createChooser(shareIntent, "Compartir archivo GPX"))
                
            } catch (e: Exception) {
                Toast.makeText(
                    this@RouteDetailActivity,
                    "Error al exportar la ruta: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    // MapView lifecycle methods
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }
    
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }
    
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
    
    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
    
    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}