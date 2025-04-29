package com.example.stepbystep.ui.routedetail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
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
                        binding.routeImage.setImageURI(Uri.fromFile(imageFile))
                        binding.routeImageCard.visibility = View.VISIBLE
                    } else {
                        binding.routeImageCard.visibility = View.GONE
                    }
                } catch (e: Exception) {
                    binding.routeImageCard.visibility = View.GONE
                }
            } ?: run {
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
        
        // Convert data to entries
        val entries = chartData.map { (distance, altitude) ->
            Entry(distance / 1000f, altitude) // Convert distance to km for x-axis
        }
        
        val dataSet = LineDataSet(entries, "Elevación").apply {
            color = getColor(R.color.turquoise)
            valueTextColor = getColor(R.color.turquoise) // Cambiado a turquesa
            lineWidth = 2f
            setDrawCircles(false)
            setDrawValues(false)
            setDrawFilled(true)
            fillColor = getColor(R.color.turquoise_light) // Color más claro para el relleno
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
            
            // Configure X axis (distance in km)
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                granularity = 0.5f  // 500m intervals
                axisMinimum = 0f
                axisLineWidth = 2f
                textColor = getColor(R.color.turquoise)
                axisLineColor = getColor(R.color.turquoise)
                
                // Añadir unidades al eje X
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return "${value.toInt()} km"
                    }
                }
            }
            
            // Configure Y axis (elevation in m)
            axisLeft.apply {
                setDrawGridLines(true)
                val turquoiseColor = getColor(R.color.turquoise)
                // Crear un color turquesa con transparencia
                val transparentTurquoise = Color.argb(
                    (0.3f * 255).toInt(),  // 30% de opacidad
                    Color.red(turquoiseColor),
                    Color.green(turquoiseColor),
                    Color.blue(turquoiseColor)
                )
                gridColor = transparentTurquoise
                axisLineWidth = 2f
                axisMinimum = chartData.minByOrNull { it.second }?.second?.let { it - 10f } ?: 0f
                textColor = getColor(R.color.turquoise)
                axisLineColor = getColor(R.color.turquoise)
                
                // Añadir unidades al eje Y
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