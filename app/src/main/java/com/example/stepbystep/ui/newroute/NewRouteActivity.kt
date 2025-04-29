package com.example.stepbystep.ui.newroute

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.stepbystep.R
import com.example.stepbystep.databinding.ActivityNewRouteBinding
import com.example.stepbystep.util.StringFormatUtils
import com.example.stepbystep.ui.saveroute.SaveRouteActivity
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class NewRouteActivity : AppCompatActivity() {

    private val TAG = "RouteTracking"

    private lateinit var binding: ActivityNewRouteBinding
    private val viewModel: NewRouteViewModel by viewModels()

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private var routeLine = mutableListOf<LatLng>()
    private var polyline: Polyline? = null
    private var isMapReady = false
    private var hasInitialLocation = false
    private var autoTrackLocation = true

    private lateinit var fusedClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    // Add these boolean variables to track visibility states
    private var statsVisible = true
    private var buttonsVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Request location permission immediately when activity opens
        checkLocationPermission()
        
        // --- Location setup ---
        fusedClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            1000L
        ).setMinUpdateIntervalMillis(500L)
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { loc ->
                    viewModel.onLocationUpdated(loc)
                    updateMapWithLocation(loc)
                    
                    // Log all tracking metrics after each location update
                    logLocationUpdate(loc)
                }
            }
        }

        // --- MapView setup --- (moved after permission request)
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { map ->
            googleMap = map
            isMapReady = true

            // Configure map settings
            googleMap.uiSettings.apply {
                isZoomControlsEnabled = true
                isCompassEnabled = true
                isMyLocationButtonEnabled = true
                isMapToolbarEnabled = true
            }

            // Enable "my location" layer if we have permission
            if (ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                googleMap.isMyLocationEnabled = true
                
                // Get initial location to center map
                fusedClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        val latLng = LatLng(location.latitude, location.longitude)
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
                        hasInitialLocation = true
                    }
                }
                
                // Start location updates if we have permission
                startLocationUpdates()
            }

            // Initialize polyline
            polyline = googleMap.addPolyline(
                PolylineOptions()
                    .width(8f)
                    .color(getColor(R.color.turquoise))
            )
        }

        // --- Botones ---
        binding.btnStartStop.setOnClickListener {
            if (viewModel.isRecording.value == true) stopRecording()
            else startRecording()
        }
        binding.btnPauseResume.setOnClickListener {
            if (viewModel.isPaused.value == true) viewModel.resumeRecording()
            else viewModel.pauseRecording()
        }
    }

    // Separate function to check and request location permission
    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    // Handle permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, set up location features
                if (isMapReady && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    googleMap.isMyLocationEnabled = true
                    fusedClient.lastLocation.addOnSuccessListener { location ->
                        location?.let {
                            val latLng = LatLng(location.latitude, location.longitude)
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
                            hasInitialLocation = true
                        }
                    }
                    
                    // Start location updates immediately to show position on map
                    startLocationUpdates()
                }
            } else {
                // Permission denied - show an explanation or disable features
                // TODO: Show a message about why location is needed
            }
        }
    }

    private fun updateMapWithLocation(location: Location) {
        if (!isMapReady) return
        
        val latLng = LatLng(location.latitude, location.longitude)
        
        // Only center map if auto-tracking is enabled or this is the first location
        if ((!hasInitialLocation || viewModel.isRecording.value == true) && autoTrackLocation) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
            hasInitialLocation = true
        }

        // Only update the route line if recording and not paused
        if (viewModel.isRecording.value == true && viewModel.isPaused.value != true) {
            routeLine.add(latLng)
            polyline?.points = routeLine
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        
        fusedClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.getMainLooper()
        )
    }

    private fun startRecording() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        
        // Clear previous route if any
        routeLine.clear()
        polyline?.points = routeLine
        
        viewModel.startRecording()
        
        // We no longer need to start location updates here since they're already running
        // The updateMapWithLocation method will handle adding points to the route
    }

    private fun stopRecording() {
        fusedClient.removeLocationUpdates(locationCallback)
        viewModel.stopRecording()
        
        // Verificar si la distancia es 0 metros o casi 0
        if ((viewModel.currentDistance.value ?: 0.0) <= 0.01) {
            // Mostrar mensaje de error con Toast
            Toast.makeText(
                this, 
                "La ruta es demasiado corta para ser guardada", 
                Toast.LENGTH_SHORT
            ).show()
            return  // Salir del método sin ir a SaveRouteActivity
        }
        
        // Prepare data to pass to SaveRouteActivity
        val intent = Intent(this, SaveRouteActivity::class.java).apply {
            putExtra("distance", viewModel.currentDistance.value ?: 0.0)
            putExtra("duration", viewModel.elapsedTimeMs.value ?: 0L)
            putExtra("elevationGain", viewModel.elevationGain.value ?: 0.0)
            putExtra("elevation", viewModel.currentElevation.value ?: 0.0)
            
            // Preparar los puntos con sus altitudes correspondientes
            val pointsList = ArrayList<LatLng>(routeLine)
            putParcelableArrayListExtra("points", pointsList)
            
            // Añadir las altitudes como un array de doubles
            val altitudes = viewModel.points.map { it.altitude }.toDoubleArray()
            putExtra("altitudes", altitudes)
        }
        
        startActivity(intent)
        // Don't call finish() here so user can go back if needed
    }

    /**
     * Logs detailed information about the current tracking state after each location update
     */
    private fun logLocationUpdate(location: Location) {
        val distance = StringFormatUtils.formatDistanceKm(viewModel.currentDistance.value ?: 0.0)
        val time = StringFormatUtils.formatDuration(viewModel.elapsedTimeMs.value ?: 0L)
        val elevation = StringFormatUtils.formatElevation(viewModel.currentElevation.value ?: 0.0)
        val elevationGain = StringFormatUtils.formatElevationGain(viewModel.elevationGain.value ?: 0.0)
        
        // Log whether we're recording and if it's paused
        val recordingStatus = when {
            viewModel.isRecording.value != true -> "Not recording"
            viewModel.isPaused.value == true -> "Recording (PAUSED)"
            else -> "Recording"
        }
        
        // Log the location coordinates and metrics
        Log.d(TAG, "Location Update: lat=${location.latitude}, lng=${location.longitude}")
        Log.d(TAG, "Tracking Status: $recordingStatus")
        Log.d(TAG, "Route Stats: Distance=$distance, Time=$time, Elevation=$elevation, Gain=$elevationGain")
        
        // Add a separator for readability in the logs
        Log.d(TAG, "--------------------------------")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_route_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menu_toggle_auto_center -> {
                autoTrackLocation = !autoTrackLocation
                item.isChecked = autoTrackLocation
                // Update the icon to visually indicate the state
                item.icon = getDrawable(
                    if (autoTrackLocation) R.drawable.ic_my_location 
                    else R.drawable.ic_my_location_off
                )
                true
            }
            R.id.menu_toggle_stats -> {
                toggleStatsVisibility(item)
                true
            }
            R.id.menu_toggle_buttons -> {
                toggleButtonsVisibility(item)
                true
            }
            R.id.menu_toggle_all -> {
                toggleAllVisibility(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Method to toggle stats card visibility
    private fun toggleStatsVisibility(item: MenuItem) {
        statsVisible = !statsVisible
        binding.statsCard.visibility = if (statsVisible) View.VISIBLE else View.GONE
        
        // Update menu item text
        item.title = if (statsVisible) "Hide Route Info" else "Show Route Info"
        item.isChecked = !statsVisible
        
        // Also update the "Toggle All" menu item
        invalidateOptionsMenu()
    }

    // Method to toggle buttons visibility
    private fun toggleButtonsVisibility(item: MenuItem) {
        buttonsVisible = !buttonsVisible
        // Find the buttons container by ID - this is the LinearLayout containing the buttons
        val buttonsContainer = binding.root.findViewById<LinearLayout>(R.id.buttonsContainer)
        buttonsContainer.visibility = if (buttonsVisible) View.VISIBLE else View.GONE
        
        // Update menu item text
        item.title = if (buttonsVisible) "Hide Control Buttons" else "Show Control Buttons"
        item.isChecked = !buttonsVisible
        
        // Also update the "Toggle All" menu item
        invalidateOptionsMenu()
    }

    // Method to toggle all UI elements at once
    private fun toggleAllVisibility(item: MenuItem) {
        // If either one is visible, hide all; otherwise, show all
        val shouldHideAll = statsVisible || buttonsVisible
        
        statsVisible = !shouldHideAll
        buttonsVisible = !shouldHideAll
        
        // Update UI visibility
        binding.statsCard.visibility = if (statsVisible) View.VISIBLE else View.GONE
        val buttonsContainer = binding.root.findViewById<LinearLayout>(R.id.buttonsContainer)
        buttonsContainer.visibility = if (buttonsVisible) View.VISIBLE else View.GONE
        
        // Update this menu item text
        item.title = if (shouldHideAll) "Show All UI Elements" else "Hide All UI Elements"
        item.isChecked = shouldHideAll
        
        // Update other menu items
        invalidateOptionsMenu()
    }

    // Refresh menu items when the menu is being prepared
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        // Update menu items based on current visibility state
        menu.findItem(R.id.menu_toggle_stats)?.apply {
            title = if (statsVisible) "Hide Route Info" else "Show Route Info"
            isChecked = !statsVisible
        }
        
        menu.findItem(R.id.menu_toggle_buttons)?.apply {
            title = if (buttonsVisible) "Hide Control Buttons" else "Show Control Buttons"
            isChecked = !buttonsVisible
        }
        
        menu.findItem(R.id.menu_toggle_all)?.apply {
            val allHidden = !statsVisible && !buttonsVisible
            title = if (allHidden) "Show All UI Elements" else "Hide All UI Elements"
            isChecked = allHidden
        }
        
        return super.onPrepareOptionsMenu(menu)
    }

    // --- Delegar ciclo de vida de MapView ---
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

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }
}
