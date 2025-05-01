package com.example.stepbystep.ui.newroute

import android.content.Context
import android.content.res.Configuration
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.stepbystep.R
import com.example.stepbystep.util.MapUtils.configureMapStyle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.*

/**
 * Controlador para el mapa en NewRouteActivity.
 * 
 * Se encarga de inicializar, configurar y actualizar el mapa,
 * así como gestionar las rutas dibujadas.
 * 
 * @param mapView Vista del mapa a controlar
 * @param context Contexto para acceder a recursos
 * @param viewModel ViewModel con los datos de la ruta
 * @param lifecycleOwner Propietario del ciclo de vida para observar LiveData
 */
class RouteMapController(
    private val mapView: MapView,
    private val context: Context,
    private val viewModel: NewRouteViewModel,
    lifecycleOwner: LifecycleOwner
) {
    private val TAG = "RouteMapController"
    
    private var googleMap: GoogleMap? = null
    private var isMapReady = false
    private var hasInitialLocation = false
    
    private lateinit var lifecycleOwner: LifecycleOwner
    
    init {
        this.lifecycleOwner = lifecycleOwner
    }
    
    // Elementos visuales en el mapa
    private var routePolyline: Polyline? = null
    private var referencePolyline: Polyline? = null
    
    /**
     * Interface para notificar eventos del mapa
     */
    interface MapCallback {
        fun onMapReady(googleMap: GoogleMap)
    }
    
    private var mapCallback: MapCallback? = null
    
    /**
     * Establece el callback para eventos del mapa
     */
    fun setMapCallback(callback: MapCallback) {
        mapCallback = callback
    }
    
    /**
     * Inicializa el controlador del mapa y configura observadores
     */
    fun initialize(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        setupMap()
        setupObservers()
    }
    
    /**
     * Configura los observadores para los cambios en el ViewModel
     */
    private fun setupObservers() {
        // Observar cambios en la lista de puntos de ruta
        viewModel.routePoints.observe(lifecycleOwner) { points ->
            Log.d(TAG, "[MAP] Observer notificado: ${points.size} puntos recibidos")
            
            if (points.isNotEmpty()) {
                updateRoutePolyline(points)
                
                // Solo centrar si el seguimiento automático está activado
                if (viewModel.autoTrackLocation.value == true) {
                    val lastPoint = points.last()
                    googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(lastPoint, 18f))
                }
            }
        }
        
        // Observar cambios en la ruta de referencia
        viewModel.referenceRoute.observe(lifecycleOwner) { points ->
            if (points.isNotEmpty()) {
                updateReferenceRoutePolyline(points)
            }
        }
    }
    
    /**
     * Configura el mapa de Google
     */
    private fun setupMap() {
        mapView.getMapAsync { map ->
            googleMap = map
            isMapReady = true
            
            // Aplicar estilo según modo día/noche
            map.configureMapStyle(context)
            
            // Configurar controles UI del mapa
            map.uiSettings.apply {
                isZoomControlsEnabled = true
                isCompassEnabled = true
                isMyLocationButtonEnabled = true
                isMapToolbarEnabled = true
            }
            
            // Crear polyline inicial vacía
            routePolyline = map.addPolyline(
                PolylineOptions()
                    .width(12f)
                    .color(ContextCompat.getColor(context, R.color.turquoise))
                    .jointType(JointType.ROUND)
            )
            
            mapCallback?.onMapReady(map)
        }
    }
    
    /**
     * Habilita el botón "Mi ubicación" y centra el mapa si es necesario
     */
    fun enableMyLocation(fusedLocationClient: FusedLocationProviderClient) {
        if (!isMapReady) return
        
        try {
            googleMap?.isMyLocationEnabled = true
            
            // Obtener la última ubicación conocida para centrar el mapa
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val latLng = LatLng(location.latitude, location.longitude)
                    googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
                    hasInitialLocation = true
                }
            }
        } catch (e: SecurityException) {
            Log.e(TAG, "Error enabling my location: ${e.message}")
        }
    }
    
    /**
     * Actualiza el mapa con una nueva ubicación
     */
    fun updateWithLocation(location: Location) {
        if (!isMapReady) return
        
        val latLng = LatLng(location.latitude, location.longitude)
        
        // Centrar el mapa automáticamente si está habilitado
        if (viewModel.autoTrackLocation.value == true) {
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18f)
            googleMap?.animateCamera(cameraUpdate)
        }
        
        // Si no tenemos una ubicación inicial, centrar el mapa
        if (!hasInitialLocation) {
            hasInitialLocation = true
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16f)
            googleMap?.moveCamera(cameraUpdate)
        }
    }
    
    /**
     * Actualiza el polyline de la ruta actual
     */
    private fun updateRoutePolyline(points: List<LatLng>) {
        if (!isMapReady || googleMap == null) {
            Log.e(TAG, "[MAP] ❌ No se puede dibujar polyline, mapa no inicializado")
            return
        }
        
        // Eliminar la polyline existente
        routePolyline?.remove()
        
        // Crear una nueva polyline con los puntos actualizados
        routePolyline = googleMap?.addPolyline(
            PolylineOptions()
                .addAll(points)
                .color(ContextCompat.getColor(context, R.color.turquoise))
                .width(12f)
                .geodesic(true)
                .jointType(JointType.ROUND)
        )
        
        Log.d(TAG, "[MAP] ✅ Polyline dibujada con ${points.size} puntos")
    }
    
    /**
     * Actualiza el polyline de la ruta de referencia
     */
    private fun updateReferenceRoutePolyline(points: List<LatLng>) {
        if (!isMapReady) return
        
        referencePolyline?.remove()
        
        if (points.isEmpty()) return
        
        val polylineOptions = PolylineOptions()
            .addAll(points)
            .color(android.graphics.Color.BLUE)
            .width(8f)
        
        referencePolyline = googleMap?.addPolyline(polylineOptions)
        
        // Ajustar zoom para mostrar toda la ruta de referencia
        if (points.size > 1) {
            try {
                val builder = LatLngBounds.Builder()
                points.forEach { builder.include(it) }
                val bounds = builder.build()
                val padding = context.resources.getDimensionPixelSize(R.dimen.map_padding)
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
            } catch (e: Exception) {
                Log.e(TAG, "Error adjusting camera for reference route", e)
            }
        }
    }
    
    /**
     * Limpia la ruta actual del mapa
     */
    fun clearRoute() {
        routePolyline?.remove()
        routePolyline = null
    }
    
    /**
     * Limpia la ruta de referencia del mapa
     */
    fun clearReferenceRoute() {
        referencePolyline?.remove()
        referencePolyline = null
    }
    
    // Métodos del ciclo de vida que deben ser llamados desde la actividad
    
    fun onStart() {
        mapView.onStart()
    }
    
    fun onResume() {
        mapView.onResume()
    }
    
    fun onPause() {
        mapView.onPause()
    }
    
    fun onStop() {
        mapView.onStop()
    }
    
    fun onDestroy() {
        mapView.onDestroy()
    }
    
    fun onLowMemory() {
        mapView.onLowMemory()
    }
    
    fun onSaveInstanceState(outState: Bundle) {
        mapView.onSaveInstanceState(outState)
    }
}