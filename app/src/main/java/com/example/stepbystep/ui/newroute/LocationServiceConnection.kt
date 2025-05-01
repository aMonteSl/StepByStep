package com.example.stepbystep.ui.newroute

import android.content.*
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.stepbystep.service.LocationTrackingService
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng

/**
 * Gestiona la conexión con el servicio de rastreo de ubicación.
 * 
 * Se encarga de:
 * - Conectar con el servicio
 * - Iniciar/detener el rastreo
 * - Procesar los datos recibidos del servicio
 * 
 * @param context Contexto para comunicarse con el servicio
 * @param viewModel ViewModel para actualizar con los datos del servicio
 */
class LocationServiceConnection(
    private val context: Context,
    private val viewModel: NewRouteViewModel
) {
    private val TAG = "LocationServiceConn"
    
    // Estado de la conexión
    private var locationServiceBound = false
    private var trackingService: LocationTrackingService? = null
    
    /**
     * Objeto de conexión al servicio
     */
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "[CONN] Servicio conectado: $name")
            val binder = service as? LocationTrackingService.LocalBinder
            if (binder == null) {
                Log.e(TAG, "[CONN] ✗ Error: Binder recibido no es del tipo esperado")
                return
            }
            
            trackingService = binder.getService()
            locationServiceBound = true
            Log.d(TAG, "[CONN] ✓ Servicio vinculado correctamente")
            
            // Sincronizar el estado actual con el ViewModel
            syncServiceStateWithViewModel()
        }
        
        override fun onServiceDisconnected(name: ComponentName?) {
            trackingService = null
            locationServiceBound = false
            Log.d(TAG, "[CONN] ✗ Servicio desconectado: $name")
        }
    }
    
    /**
     * Sincroniza completamente el estado del servicio con el ViewModel
     */
    fun syncServiceStateWithViewModel() {
        trackingService?.let { service ->
            if (service.isTracking()) {
                // Recopilar todos los datos acumulados del servicio
                val distance = service.getDistance() / 1000.0  // Convertir a km
                val elapsedTime = service.getElapsedTime()
                val elevation = service.getCurrentElevation()
                val elevationGain = service.getElevationGain()
                val routePoints = service.getRoutePoints()  // Recuperar todos los puntos de la ruta
                
                Log.d(TAG, "[SYNC] Recuperando estado del servicio: " +
                      "dist=${distance}km, tiempo=${elapsedTime}ms, " +
                      "elev=${elevation}m, gain=${elevationGain}m, " +
                      "puntos=${routePoints.size}")
                
                // Actualizar el ViewModel con todos los datos acumulados
                viewModel.restoreTrackingState(
                    distance,
                    elapsedTime,
                    elevation,
                    elevationGain,
                    routePoints
                )
                
                // Actualizar estado de grabación
                if (!viewModel.isRecording.value!!) {
                    viewModel.startRecording(forceReset = false)  // No reiniciar contadores
                }
                
                if (service.isPaused()) {
                    viewModel.pauseRecording()
                }
            }
        }
    }
    
    /**
     * Receptor de broadcast para actualizaciones de ubicación
     */
    private val locationReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context?, intent: Intent?) {
            // Log sin condiciones para ver que onReceive es llamado
            Log.d(TAG, "[RECV] onReceive llamado con intent: ${intent?.action}")
            
            if (intent?.action == LocationTrackingService.ACTION_LOCATION_BROADCAST) {
                // Extraer valores uno a uno verificando que no sean nulos
                val location = intent.getParcelableExtra<Location>(LocationTrackingService.EXTRA_LOCATION)
                val distance = intent.getFloatExtra(LocationTrackingService.EXTRA_DISTANCE, 0f)
                val timeMs = intent.getLongExtra(LocationTrackingService.EXTRA_TIME, 0L)
                val elevation = intent.getDoubleExtra(LocationTrackingService.EXTRA_ELEVATION, 0.0)
                val elevationGain = intent.getDoubleExtra(LocationTrackingService.EXTRA_ELEVATION_GAIN, 0.0)
                
                Log.d(TAG, "[RECV] ✅ Datos recibidos: dist=$distance m, tiempo=$timeMs ms, elev=$elevation m")
                
                viewModel.updateTracking(
                    location,
                    distance.toDouble() / 1000.0, // Convertir a km
                    timeMs,
                    elevation,
                    elevationGain
                )
                
                // Notificar al callback si existe
                location?.let { locationCallback?.onLocationUpdated(it) }
            }
        }
    }
    
    /**
     * Interface para notificar sobre actualizaciones de ubicación
     */
    interface LocationCallback {
        fun onLocationUpdated(location: Location)
    }
    
    private var locationCallback: LocationCallback? = null
    
    /**
     * Establece el callback para notificaciones de ubicación
     */
    fun setLocationCallback(callback: LocationCallback) {
        locationCallback = callback
    }
    
    /**
     * Registra el receptor de broadcast al iniciar
     */
    fun registerReceiver() {
        val filter = IntentFilter(LocationTrackingService.ACTION_LOCATION_BROADCAST)
        try {
            // Log ANTES del registro para verificar que llega aquí
            Log.d(TAG, "[CONN] Intentando registrar BroadcastReceiver para acción: ${LocationTrackingService.ACTION_LOCATION_BROADCAST}")
            
            // Usar registerReceiver con try-catch para capturar cualquier error
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.registerReceiver(locationReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
            } else {
                context.registerReceiver(locationReceiver, filter)
            }
            
            Log.d(TAG, "[CONN] ✅ BroadcastReceiver registrado exitosamente")
        } catch (e: Exception) {
            Log.e(TAG, "[CONN] ❌ ERROR al registrar receptor: ${e.message}", e)
        }
    }
    
    /**
     * Desregistra el receptor de broadcast al finalizar
     */
    fun unregisterReceiver() {
        try {
            context.unregisterReceiver(locationReceiver)
            Log.d(TAG, "[CONN] ✅ BroadcastReceiver desregistrado")
        } catch (_: IllegalArgumentException) {
            // ya estaba desregistrado
        }
    }
    
    /**
     * Conecta con el servicio
     */
    fun bindService() {
        val intent = Intent(context, LocationTrackingService::class.java)
        Log.d(TAG, "[CONN] Intentando vincular al servicio...")
        val result = context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        Log.d(TAG, "[CONN] Resultado vinculación: $result")
    }
    
    /**
     * Desconecta del servicio
     */
    fun unbindService() {
        if (locationServiceBound) {
            context.unbindService(serviceConnection)
            locationServiceBound = false
            trackingService = null
        }
    }
    
    /**
     * Inicia el servicio de rastreo de ubicación
     * @param backgroundEnabled Indica si el rastreo debe continuar en segundo plano
     */
    fun startLocationTracking(backgroundEnabled: Boolean = true) {
        val trackingIntent = Intent(context, LocationTrackingService::class.java).apply {
            action = LocationTrackingService.ACTION_START_TRACKING
            putExtra("BACKGROUND_ENABLED", backgroundEnabled)
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(trackingIntent)
        } else {
            context.startService(trackingIntent)
        }
        
        context.bindService(trackingIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }
    
    /**
     * Detiene el servicio de rastreo de ubicación
     */
    fun stopLocationTracking() {
        val intent = Intent(context, LocationTrackingService::class.java).apply {
            action = LocationTrackingService.ACTION_STOP_TRACKING
        }
        context.stopService(intent)
        
        if (locationServiceBound) {
            context.unbindService(serviceConnection)
            locationServiceBound = false
            trackingService = null
        }
    }
    
    /**
     * Pausa el rastreo de ubicación
     */
    fun pauseTracking() {
        trackingService?.pauseTracking() ?: run {
            // Si no tenemos conexión directa, usar Intent
            val intent = Intent(context, LocationTrackingService::class.java).apply {
                action = LocationTrackingService.ACTION_PAUSE_TRACKING
            }
            context.startService(intent)
        }
    }
    
    /**
     * Reanuda el rastreo de ubicación
     */
    fun resumeTracking() {
        trackingService?.resumeTracking() ?: run {
            // Si no tenemos conexión directa, usar Intent
            val intent = Intent(context, LocationTrackingService::class.java).apply {
                action = LocationTrackingService.ACTION_RESUME_TRACKING
            }
            context.startService(intent)
        }
    }
    
    /**
     * Indica si el servicio está vinculado
     */
    fun isServiceBound(): Boolean = locationServiceBound
    
    /**
     * Indica si el servicio está rastreando activamente
     */
    fun isTracking(): Boolean = trackingService?.isTracking() ?: false
    
    /**
     * Indica si el rastreo está en pausa
     */
    fun isPaused(): Boolean = trackingService?.isPaused() ?: false
    
    /**
     * Obtiene la distancia actual registrada por el servicio
     */
    fun getDistance(): Float = trackingService?.getDistance() ?: 0f
    
    /**
     * Obtiene el tiempo transcurrido registrado por el servicio
     */
    fun getElapsedTime(): Long = trackingService?.getElapsedTime() ?: 0L
    
    /**
     * Obtiene la elevación actual registrada por el servicio
     */
    fun getCurrentElevation(): Double {
        return trackingService?.getCurrentElevation() ?: 0.0
    }
    
    /**
     * Obtiene el servicio de rastreo, si está vinculado
     */
    fun getService(): LocationTrackingService? = trackingService
}