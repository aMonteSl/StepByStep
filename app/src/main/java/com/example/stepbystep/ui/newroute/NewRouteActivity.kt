package com.example.stepbystep.ui.newroute

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.stepbystep.R
import com.example.stepbystep.databinding.ActivityNewRouteBinding
import com.example.stepbystep.util.StringFormatUtils
import com.example.stepbystep.ui.saveroute.SaveRouteActivity
import com.example.stepbystep.util.GpxParser
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
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
    private var referencePolyline: Polyline? = null
    private var isMapReady = false
    private var hasInitialLocation = false
    private var autoTrackLocation = true

    private lateinit var fusedClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private var statsVisible = true
    private var buttonsVisible = true

    private val selectGpxLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            loadReferenceGpx(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        checkLocationPermission()

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
                    logLocationUpdate(loc)
                }
            }
        }

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { map ->
            googleMap = map
            isMapReady = true

            googleMap.uiSettings.apply {
                isZoomControlsEnabled = true
                isCompassEnabled = true
                isMyLocationButtonEnabled = true
                isMapToolbarEnabled = true
            }

            if (ActivityCompat.checkSelfPermission(
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

                startLocationUpdates()
            }

            polyline = googleMap.addPolyline(
                PolylineOptions()
                    .width(8f)
                    .color(getColor(R.color.turquoise))
            )
        }

        binding.btnStartStop.setOnClickListener {
            if (viewModel.isRecording.value == true) stopRecording()
            else startRecording()
        }
        binding.btnPauseResume.setOnClickListener {
            if (viewModel.isPaused.value == true) viewModel.resumeRecording()
            else viewModel.pauseRecording()
        }

        binding.fabSelectReference.setOnClickListener {
            openGpxFilePicker()
        }

        binding.chipReferenceRoute.setOnClickListener {
            viewModel.toggleReferenceRouteVisibility()
        }

        binding.chipReferenceRoute.setOnCloseIconClickListener {
            viewModel.clearReferenceRoute()
            referencePolyline?.remove()
            referencePolyline = null
        }

        viewModel.referencePoints.observe(this) { points ->
            updateReferenceRouteOnMap(points)
        }

        viewModel.referenceRouteVisible.observe(this) { visible ->
            referencePolyline?.isVisible = visible
        }
    }

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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

                    startLocationUpdates()
                }
            } else {
                // TODO: Show a message about why location is needed
            }
        }
    }

    private fun updateMapWithLocation(location: Location) {
        if (!isMapReady) return

        val latLng = LatLng(location.latitude, location.longitude)

        if ((!hasInitialLocation || viewModel.isRecording.value == true) && autoTrackLocation) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
            hasInitialLocation = true
        }

        if (viewModel.isRecording.value == true && viewModel.isPaused.value != true) {
            routeLine.add(latLng)

            if (polyline == null) {
                polyline = googleMap.addPolyline(
                    PolylineOptions()
                        .addAll(routeLine)
                        .color(android.graphics.Color.RED)
                        .width(10f)
                )
            } else {
                polyline?.points = routeLine
            }
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

        routeLine.clear()
        polyline?.points = routeLine

        viewModel.startRecording()
    }

    private fun stopRecording() {
        fusedClient.removeLocationUpdates(locationCallback)
        viewModel.stopRecording()

        val intent = Intent(this, SaveRouteActivity::class.java).apply {
            putExtra("distance", viewModel.currentDistance.value ?: 0.0)
            putExtra("duration", viewModel.elapsedTimeMs.value ?: 0L)
            putExtra("elevationGain", viewModel.elevationGain.value ?: 0.0)
            putExtra("elevation", viewModel.currentElevation.value ?: 0.0)

            val pointsList = ArrayList<LatLng>(routeLine)
            putParcelableArrayListExtra("points", pointsList)

            val altitudes = viewModel.points.map { it.altitude }.toDoubleArray()
            putExtra("altitudes", altitudes)
        }

        startActivity(intent)
    }

    private fun logLocationUpdate(location: Location) {
        val distance = StringFormatUtils.formatDistanceKm(viewModel.currentDistance.value ?: 0.0)
        val time = StringFormatUtils.formatDuration(viewModel.elapsedTimeMs.value ?: 0L)
        val elevation = StringFormatUtils.formatElevation(viewModel.currentElevation.value ?: 0.0)
        val elevationGain = StringFormatUtils.formatElevationGain(viewModel.elevationGain.value ?: 0.0)

        val recordingStatus = when {
            viewModel.isRecording.value != true -> "Not recording"
            viewModel.isPaused.value == true -> "Recording (PAUSED)"
            else -> "Recording"
        }

        Log.d(TAG, "Location Update: lat=${location.latitude}, lng=${location.longitude}")
        Log.d(TAG, "Tracking Status: $recordingStatus")
        Log.d(TAG, "Route Stats: Distance=$distance, Time=$time, Elevation=$elevation, Gain=$elevationGain")
        Log.d(TAG, "--------------------------------")
    }

    private fun openGpxFilePicker() {
        selectGpxLauncher.launch("application/gpx+xml")
    }

    private fun loadReferenceGpx(uri: Uri) {
        try {
            val gpxData = GpxParser.parse(this, uri) ?: run {
                Toast.makeText(this, R.string.gpx_parse_error, Toast.LENGTH_SHORT).show()
                return
            }

            val points = gpxData.points.map { LatLng(it.latitude, it.longitude) }
            viewModel.setReferenceRoute(points, gpxData.name)

            Toast.makeText(this, "Ruta de referencia cargada", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e(TAG, "Error loading reference GPX", e)
            Toast.makeText(this, "Error al cargar el archivo GPX", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateReferenceRouteOnMap(points: List<LatLng>) {
        if (!isMapReady) return

        referencePolyline?.remove()

        if (points.isEmpty()) return

        val polylineOptions = PolylineOptions()
            .addAll(points)
            .color(android.graphics.Color.BLUE)
            .width(8f)

        referencePolyline = googleMap.addPolyline(polylineOptions)

        val builder = LatLngBounds.Builder()
        points.forEach { builder.include(it) }

        if (points.size > 1) {
            try {
                val bounds = builder.build()
                val padding = resources.getDimensionPixelSize(R.dimen.map_padding)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
            } catch (e: Exception) {
                Log.e(TAG, "Error adjusting camera for reference route", e)
            }
        }
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

    private fun toggleStatsVisibility(item: MenuItem) {
        statsVisible = !statsVisible
        binding.statsCard.visibility = if (statsVisible) View.VISIBLE else View.GONE

        item.title = if (statsVisible) "Hide Route Info" else "Show Route Info"
        item.isChecked = !statsVisible

        invalidateOptionsMenu()
    }

    private fun toggleButtonsVisibility(item: MenuItem) {
        buttonsVisible = !buttonsVisible
        val buttonsContainer = binding.root.findViewById<LinearLayout>(R.id.buttonsContainer)
        buttonsContainer.visibility = if (buttonsVisible) View.VISIBLE else View.GONE

        item.title = if (buttonsVisible) "Hide Control Buttons" else "Show Control Buttons"
        item.isChecked = !buttonsVisible

        invalidateOptionsMenu()
    }

    private fun toggleAllVisibility(item: MenuItem) {
        val shouldHideAll = statsVisible || buttonsVisible

        statsVisible = !shouldHideAll
        buttonsVisible = !shouldHideAll

        binding.statsCard.visibility = if (statsVisible) View.VISIBLE else View.GONE
        val buttonsContainer = binding.root.findViewById<LinearLayout>(R.id.buttonsContainer)
        buttonsContainer.visibility = if (buttonsVisible) View.VISIBLE else View.GONE

        item.title = if (shouldHideAll) "Show All UI Elements" else "Hide All UI Elements"
        item.isChecked = shouldHideAll

        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
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
