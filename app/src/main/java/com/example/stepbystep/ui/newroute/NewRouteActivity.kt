package com.example.stepbystep.ui.newroute

import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.os.Handler
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
import com.example.stepbystep.ui.saveroute.SaveRouteActivity
import com.example.stepbystep.util.GpxParser
import com.example.stepbystep.util.LocationPermissionManager
import com.example.stepbystep.util.LocationPermissionManager.PermissionCallback
import com.google.android.gms.location.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import androidx.activity.result.contract.ActivityResultContracts

/**
 * Actividad principal para la creación y grabación de nuevas rutas.
 * 
 * Esta actividad permite al usuario:
 * - Iniciar/pausar/detener la grabación de una ruta
 * - Visualizar en tiempo real el trazado en un mapa
 * - Ver estadísticas de la ruta (distancia, tiempo, elevación)
 * - Cargar una ruta GPX de referencia
 * 
 * Gestiona permisos de ubicación, comunicación con el servicio de rastreo,
 * y la actualización en tiempo real del mapa y estadísticas.
 */
class NewRouteActivity : AppCompatActivity(), 
    PermissionCallback, 
    RouteMapController.MapCallback,
    LocationServiceConnection.LocationCallback {

    private val TAG = "RouteTracking"

    // Componentes de UI y estado
    private lateinit var binding: ActivityNewRouteBinding
    private val viewModel: NewRouteViewModel by viewModels()

    // Componentes auxiliares extraídos
    private lateinit var permissionManager: LocationPermissionManager
    private lateinit var mapController: RouteMapController
    private lateinit var serviceConnection: LocationServiceConnection

    // Cliente de ubicación para actualizaciones en primer plano
    private lateinit var fusedClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    /**
     * Launcher para seleccionar archivos GPX como ruta de referencia
     */
    private val selectGpxLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { loadReferenceGpx(it) }
    }

    /**
     * Inicialización de la actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración de la barra de herramientas
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Configuración de data binding
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Inicializar componentes auxiliares
        permissionManager = LocationPermissionManager(this)
        permissionManager.setPermissionCallback(this)

        mapController = RouteMapController(binding.mapView, this, viewModel, this)
        mapController.setMapCallback(this)
        mapController.initialize(savedInstanceState)

        serviceConnection = LocationServiceConnection(this, viewModel)
        serviceConnection.setLocationCallback(this)
        serviceConnection.registerReceiver()
        
        // Inicializar cliente de ubicación para actualizaciones en primer plano
        setupLocationComponents()
        
        // Configurar la interfaz de usuario
        setupButtons()
        setupObservers()
        
        // Verificar permisos
        permissionManager.checkAndRequestNotificationPermission()
        permissionManager.checkAndRequestLocationPermissions()
    }
    
    /**
     * Configura los componentes de ubicación para actualizaciones en primer plano
     */
    private fun setupLocationComponents() {
        fusedClient = LocationServices.getFusedLocationProviderClient(this)
        
        Log.d(TAG, "[ACTIVITY] Configurando componentes de ubicación...")
        
        // Configurar la solicitud de ubicación
        locationRequest = LocationRequest.Builder(1000L) // Intervalo en milisegundos
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setMinUpdateIntervalMillis(500L)
            .build()
        
        // Configurar el callback de ubicación
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let { loc ->
                    Log.d(TAG, "[ACTIVITY] LocationCallback - Ubicación recibida: ${loc.latitude}, ${loc.longitude}, alt=${loc.altitude}")
                    
                    // Solo actualizar el mapa, NO el viewModel
                    Log.d(TAG, "[ACTIVITY] Actualizando SOLO el mapa (no viewModel)")
                    onLocationUpdated(loc)
                }
            }
        }
        
        // Configurar el receptor de actualizaciones del servicio
        serviceConnection.setLocationCallback(this)
        Log.d(TAG, "[ACTIVITY] ✓ Componentes de ubicación configurados")
    }

    /**
     * Configura los listeners para los botones de la UI
     */
    private fun setupButtons() {
        // Botón iniciar/detener
        binding.btnStartStop.setOnClickListener {
            if (!permissionManager.hasForegroundLocationPermissions()) {
                permissionManager.requestLocationPermissions()
            } else {
                toggleTrackingState()
            }
        }

        // Botón pausar/reanudar
        binding.btnPauseResume.setOnClickListener {
            if (viewModel.isPaused.value == true) {
                viewModel.resumeRecording()
                serviceConnection.resumeTracking()
            } else {
                viewModel.pauseRecording()
                serviceConnection.pauseTracking()
            }
        }

        // Botón para seleccionar ruta de referencia
        binding.fabSelectReference.setOnClickListener {
            openGpxFilePicker()
        }

        // Chip de ruta de referencia
        binding.chipReferenceRoute.setOnClickListener {
            viewModel.toggleReferenceRouteVisibility()
        }

        // Eliminar ruta de referencia
        binding.chipReferenceRoute.setOnCloseIconClickListener {
            viewModel.clearReferenceRoute()
            mapController.clearReferenceRoute()
        }
    }

    /**
     * Configura los observers para los LiveData del ViewModel
     */
    private fun setupObservers() {
        // Estado de grabación
        viewModel.isRecording.observe(this) { isRecording ->
            binding.btnStartStop.text = if (isRecording) "DETENER" else "INICIAR"
            binding.btnStartStop.icon = getDrawable(
                if (isRecording) R.drawable.ic_stop
                else R.drawable.ic_play
            )
            updateButtonVisibility()
        }

        // Estado de pausa
        viewModel.isPaused.observe(this) { isPaused ->
            binding.btnPauseResume.text = if (isPaused) "REANUDAR" else "PAUSAR"
            binding.btnPauseResume.icon = getDrawable(
                if (isPaused) R.drawable.ic_play
                else R.drawable.ic_pause
            )
        }

        // Cambios en la ruta de referencia
        viewModel.referenceRoute.observe(this) { route ->
            if (route.isEmpty()) {
                binding.chipReferenceRoute.visibility = View.GONE
            } else {
                binding.chipReferenceRoute.visibility = View.VISIBLE
                binding.chipReferenceRoute.text = viewModel.referenceRouteName.value ?: "Ruta de referencia"
            }
        }

        // Autocentrado del mapa
        viewModel.autoTrackLocation.observe(this) { autoTrack ->
            // Actualizar el ícono del menú si se hace necesario
            invalidateOptionsMenu()
        }

        // Visibilidad de estadísticas y botones
        viewModel.statsVisible.observe(this) { visible ->
            binding.statsCard.visibility = if (visible) View.VISIBLE else View.GONE
            invalidateOptionsMenu()
        }

        viewModel.buttonsVisible.observe(this) { visible ->
            val buttonsContainer = binding.root.findViewById<LinearLayout>(R.id.buttonsContainer)
            buttonsContainer.visibility = if (visible) View.VISIBLE else View.GONE
            invalidateOptionsMenu()
        }

        updateButtonVisibility()

        // Observador para la elevación
        viewModel.currentElevation.observe(this) { elevation ->
            Log.d(TAG, "[OBSERVE] Elevación LiveData cambió a: $elevation")
            logViewModelState()
        }
    }

    /**
     * Inicia las actualizaciones periódicas de ubicación en primer plano
     */
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

    /**
     * Alterna entre iniciar y detener la grabación de la ruta
     */
    private fun toggleTrackingState() {
        if (viewModel.isRecording.value == true) {
            // Detener grabación
            fusedClient.removeLocationUpdates(locationCallback)
            viewModel.stopRecording()

            // Detener servicio
            serviceConnection.stopLocationTracking()

            // Preparar datos para la siguiente pantalla
            navigateToSaveScreen()
        } else {
            // Iniciar grabación
            // Reiniciar el estado del mapa
            mapController.clearRoute()
            
            // Iniciar grabación en el ViewModel
            viewModel.startRecording()

            // Iniciar servicio de rastreo
            if (permissionManager.hasForegroundLocationPermissions()) {
                val useBackgroundTracking = permissionManager.hasBackgroundLocationPermission()
                serviceConnection.startLocationTracking(backgroundEnabled = useBackgroundTracking)
                
                // Si no tenemos permiso de segundo plano, mostrar mensaje informativo
                if (!useBackgroundTracking) {
                    Toast.makeText(
                        this,
                        "La grabación se detendrá si la app pasa a segundo plano",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                permissionManager.requestLocationPermissions()
            }
        }

        updateButtonVisibility()
    }

    /**
     * Navega a la pantalla de guardar ruta
     */
    private fun navigateToSaveScreen() {
        val bundle = viewModel.prepareRouteDataForSave()
        val intent = Intent(this, SaveRouteActivity::class.java)
        intent.putExtras(bundle)  // Ahora no hay ambigüedad al especificar el tipo
        startActivity(intent)
    }

    /**
     * Actualiza la visibilidad del botón de pausar/reanudar
     */
    private fun updateButtonVisibility() {
        binding.btnPauseResume.visibility =
            if (viewModel.isRecording.value == true) View.VISIBLE else View.GONE
    }

    /**
     * Abre el selector de archivos para elegir un GPX de referencia
     */
    private fun openGpxFilePicker() {
        selectGpxLauncher.launch("application/gpx+xml")
    }

    /**
     * Carga un archivo GPX como ruta de referencia
     */
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

    /**
     * Inicia el rastreo en primer plano sin persistencia en segundo plano
     */
    private fun startForegroundTrackingOnly() {
        Toast.makeText(
            this,
            "Rastreando sólo en primer plano. El rastreo se detendrá si cambias de app.",
            Toast.LENGTH_LONG
        ).show()

        serviceConnection.startLocationTracking(backgroundEnabled = false)
    }

    // Implementación de callbacks de permisos

    override fun onForegroundLocationPermissionGranted() {
        mapController.enableMyLocation(fusedClient)
        startLocationUpdates()
    }

    override fun onBackgroundLocationPermissionGranted() {
        serviceConnection.startLocationTracking(backgroundEnabled = true)
    }

    override fun onBackgroundLocationPermissionDenied() {
        startForegroundTrackingOnly()
    }

    override fun onPermissionDenied() {
        // No hacer nada especial, el diálogo ya informó al usuario
    }

    // Implementación de callbacks del mapa

    override fun onMapReady(googleMap: GoogleMap) {
        if (permissionManager.hasForegroundLocationPermissions()) {
            mapController.enableMyLocation(fusedClient)
        }
    }

    // Implementación de callbacks de ubicación

    override fun onLocationUpdated(location: Location) {
        Log.d(TAG, "[ACTIVITY] onLocationUpdated(): ${location.latitude}, ${location.longitude}, alt=${location.altitude}")
        
        mapController.updateWithLocation(location)
        
        // Traza adicional sobre la elevación que se está procesando
        Log.d(TAG, "[ACTIVITY] Elevación en la ubicación: ${location.altitude}")
        Log.d(TAG, "[ACTIVITY] Elevación actual en viewModel: ${viewModel.currentElevation.value}")
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        
        if (intent.action == "com.example.stepbystep.NOTIFICATION_CLICK") {
            Log.d(TAG, "[ACTIVITY] Restaurando desde notificación")
            
            // Asegurarnos de registrar el receptor de broadcast si no lo está
            serviceConnection.registerReceiver()
            
            // Vincular o sincronizar con el servicio
            if (!serviceConnection.isServiceBound()) {
                serviceConnection.bindService()
                // La sincronización ocurrirá en el callback onServiceConnected
            } else {
                // Sincronización inmediata si ya está vinculado
                serviceConnection.syncServiceStateWithViewModel()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mapController.onPause()
        
        // Notificar al servicio que la app está pasando a segundo plano
        if (serviceConnection.isServiceBound() && serviceConnection.isTracking()) {
            serviceConnection.getService()?.adjustForBackgroundMode(true)
            Log.d(TAG, "[ACTIVITY] Notificado paso a segundo plano al servicio")
        }
    }

    override fun onResume() {
        super.onResume()
        mapController.onResume()
        
        // Notificar al servicio que la app está volviendo a primer plano
        if (serviceConnection.isServiceBound() && serviceConnection.isTracking()) {
            serviceConnection.getService()?.adjustForBackgroundMode(false)
            Log.d(TAG, "[ACTIVITY] Notificado regreso a primer plano al servicio")
        }
    }

    // Métodos para el manejo del menú

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
                viewModel.toggleAutoTrackLocation()
                true
            }
            R.id.menu_toggle_stats -> {
                viewModel.toggleStatsVisibility()
                true
            }
            R.id.menu_toggle_buttons -> {
                viewModel.toggleButtonsVisibility()
                true
            }
            R.id.menu_toggle_all -> {
                viewModel.toggleAllVisibility()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.menu_toggle_auto_center)?.apply {
            isChecked = viewModel.autoTrackLocation.value == true
            icon = getDrawable(
                if (viewModel.autoTrackLocation.value == true) R.drawable.ic_my_location
                else R.drawable.ic_my_location_off
            )
        }

        menu.findItem(R.id.menu_toggle_stats)?.apply {
            title = if (viewModel.statsVisible.value == true) "Hide Route Info" else "Show Route Info"
            isChecked = viewModel.statsVisible.value != true
        }

        menu.findItem(R.id.menu_toggle_buttons)?.apply {
            title = if (viewModel.buttonsVisible.value == true) "Hide Control Buttons" else "Show Control Buttons"
            isChecked = viewModel.buttonsVisible.value != true
        }

        menu.findItem(R.id.menu_toggle_all)?.apply {
            val allHidden = viewModel.statsVisible.value != true && viewModel.buttonsVisible.value != true
            title = if (allHidden) "Show All UI Elements" else "Hide All UI Elements"
            isChecked = allHidden
        }

        return super.onPrepareOptionsMenu(menu)
    }

    // Métodos para el manejo del ciclo de vida

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "[ACTIVITY] onStart()")
        mapController.onStart()
        serviceConnection.registerReceiver() // Registrar aquí
        serviceConnection.bindService()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "[ACTIVITY] onStop()")
        mapController.onStop()
        serviceConnection.unregisterReceiver() // Desregistrar aquí
        serviceConnection.unbindService()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapController.onDestroy()
        serviceConnection.unregisterReceiver()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapController.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapController.onSaveInstanceState(outState)
    }

    /**
     * Registra el estado actual del ViewModel en el log.
     */
    private fun logViewModelState() {
        Log.d(TAG, "╔═════════════════════════════════════════════╗")
        Log.d(TAG, "║         ESTADO ACTUAL DEL VIEWMODEL         ║")
        Log.d(TAG, "╠═════════════════════════════════════════════╣")
        Log.d(TAG, "║ Grabando:           ${viewModel.isRecording.value}") 
        Log.d(TAG, "║ Pausado:            ${viewModel.isPaused.value}")
        Log.d(TAG, "║ Distancia:          ${viewModel.currentDistance.value} km")
        Log.d(TAG, "║ Tiempo:             ${viewModel.elapsedTimeMs.value} ms")
        Log.d(TAG, "║ Elevación:          ${viewModel.currentElevation.value} m")
        Log.d(TAG, "║ Ganancia Elevación: ${viewModel.elevationGain.value} m")
        Log.d(TAG, "║ Puntos Registrados: ${viewModel.routePoints.value?.size}")
        Log.d(TAG, "╚═════════════════════════════════════════════╝")
        
        // Verificar si la vinculación de datos está activa
        Log.d(TAG, "Estado de Data Binding:")
        Log.d(TAG, " - binding.lifecycleOwner: ${binding.lifecycleOwner != null}")
        Log.d(TAG, " - binding.viewModel: ${binding.viewModel != null}")
        Log.d(TAG, " - XML tvRouteElevation: ${binding.tvRouteElevation.text}")
        Log.d(TAG, " - XML tvRouteDistance: ${binding.tvRouteDistance.text}")
    }
}
