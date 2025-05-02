package com.example.stepbystep.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.Location
import android.os.*
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.stepbystep.R
import com.example.stepbystep.ui.newroute.NewRouteActivity
import com.example.stepbystep.util.StringFormatUtils
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.util.concurrent.TimeUnit

/**
 * Servicio en primer plano que se encarga del rastreo de ubicación del usuario.
 * Gestiona la obtención periódica de coordenadas GPS, cálculo de distancia, 
 * elevación y tiempo, incluso cuando la app está en segundo plano.
 */
class LocationTrackingService : Service() {
    private val TAG = "LocationTrackingService"
    
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    
    // WakeLock para mantener la CPU activa durante el rastreo
    private var wakeLock: PowerManager.WakeLock? = null
    
    // Variables para almacenar el estado del rastreo
    private var isTracking = false      // Indica si el servicio está rastreando activamente
    private var distanceInMeters = 0f   // Distancia total recorrida en metros
    private var lastLocation: Location? = null  // Última ubicación registrada
    private var startTimeMillis = 0L    // Timestamp de inicio del rastreo
    private var elapsedTimeMillis = 0L  // Tiempo transcurrido en milisegundos
    private var isPaused = false        // Indica si el rastreo está en pausa
    private var pauseStartTimeMillis = 0L  // Timestamp cuando se pausó el rastreo
    private var backgroundEnabled = true // Indica si el rastreo debe continuar en segundo plano
    
    // Variables para el seguimiento de la elevación
    private var currentElevation = 0.0      // Elevación actual en metros
    private var elevationGain = 0.0         // Ganancia acumulada de elevación en metros
    private var lastProcessedElevation = 0.0  // Última elevación procesada para calcular ganancia

    // Añadir lista para almacenar todos los puntos procesados
    private val routePoints = mutableListOf<LatLng>()

    // Handler para actualización periódica de tiempo en segundo plano
    private val handler = Handler(Looper.getMainLooper())
    private val timeUpdateRunnable = object : Runnable {
        override fun run() {
            if (isTracking && !isPaused) {
                updateElapsedTime()
                updateNotification()
                // Programar la próxima actualización
                handler.postDelayed(this, 1000)
            }
        }
    }

    // Constantes para la comunicación y configuración del servicio
    companion object {
        const val ACTION_LOCATION_BROADCAST = "com.example.stepbystep.LOCATION_BROADCAST"
        const val EXTRA_LOCATION = "extra_location"
        const val EXTRA_DISTANCE = "extra_distance"  
        const val EXTRA_TIME = "extra_time"          
        const val EXTRA_ELEVATION = "extra_elevation"
        const val EXTRA_ELEVATION_GAIN = "extra_elevation_gain"
        
        // Acciones para control del servicio mediante Intents
        const val ACTION_START_TRACKING = "com.example.stepbystep.START_TRACKING"
        const val ACTION_STOP_TRACKING = "com.example.stepbystep.STOP_TRACKING"
        const val ACTION_PAUSE_TRACKING = "com.example.stepbystep.PAUSE_TRACKING"
        const val ACTION_RESUME_TRACKING = "com.example.stepbystep.RESUME_TRACKING"
        
        // Añadir estas constantes faltantes
        const val NOTIFICATION_ID = 1001
        const val CHANNEL_ID = "location_tracking_channel"
    }
    
    // Binder para comunicación con la actividad
    private val binder = LocalBinder()
    
    inner class LocalBinder : Binder() {
        // Permite a las actividades vinculadas obtener una referencia a este servicio
        fun getService(): LocationTrackingService = this@LocationTrackingService
    }
    
    /**
     * Inicialización del servicio. Se ejecuta una sola vez cuando el servicio se crea.
     */
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service onCreate")
        
        createNotificationChannel()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        createLocationRequest()
        
        // Configuración del callback que procesará las actualizaciones de ubicación
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                if (isTracking && !isPaused) {
                    result.lastLocation?.let { location ->
                        handleNewLocation(location)
                    }
                }
            }
        }
        
        // Inicializar WakeLock para mantener el dispositivo procesando en segundo plano
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(
            PowerManager.PARTIAL_WAKE_LOCK,
            "StepByStep::LocationTrackingWakeLock"
        )
    }
    
    /**
     * Maneja los intents que inician o controlan el servicio.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service onStartCommand: ${intent?.action}")
        
        when (intent?.action) {
            ACTION_START_TRACKING -> {
                if (!isTracking) {
                    backgroundEnabled = intent.getBooleanExtra("BACKGROUND_ENABLED", true)
                    startLocationTracking()
                    startForeground(NOTIFICATION_ID, createNotification())
                }
            }
            ACTION_STOP_TRACKING -> {
                stopLocationTracking()
                stopSelf()
            }
            ACTION_PAUSE_TRACKING -> {
                pauseTracking()
            }
            ACTION_RESUME_TRACKING -> {
                resumeTracking()
            }
        }
        
        return START_STICKY // Esto hace que el sistema recree el servicio si es terminado
    }
    
    /**
     * Devuelve el binder que permite a las actividades comunicarse con el servicio.
     */
    override fun onBind(intent: Intent): IBinder {
        return binder
    }
    
    /**
     * Limpieza cuando el servicio es destruido.
     */
    override fun onDestroy() {
        Log.d(TAG, "Service onDestroy")
        stopLocationTracking()
        releaseWakeLock()
        serviceScope.cancel()
        handler.removeCallbacks(timeUpdateRunnable)
        super.onDestroy()
    }
    
    /**
     * Adquiere el WakeLock para mantener la CPU activa durante el rastreo.
     */
    private fun acquireWakeLock() {
        if (wakeLock == null) {
            val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
            wakeLock = powerManager.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,
                "StepByStep:LocationTrackingWakeLock"
            ).apply {
                setReferenceCounted(false)
                acquire(TimeUnit.HOURS.toMillis(3)) // Máximo 3 horas o ajustar según caso de uso
            }
            Log.d(TAG, "WakeLock acquired")
        }
    }
    
    /**
     * Libera el WakeLock cuando ya no es necesario.
     */
    private fun releaseWakeLock() {
        wakeLock?.apply {
            if (isHeld) {
                release()
                Log.d(TAG, "WakeLock released")
            }
        }
    }
    
    /**
     * Crea el canal de notificaciones requerido para Android 8.0+.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val channel = NotificationChannel(
                CHANNEL_ID,
                name,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                lightColor = Color.BLUE
                setShowBadge(false)
            }
            
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    /**
     * Crea la notificación persistente del servicio en primer plano.
     */
    private fun createNotification(): Notification {
        // Crear un Intent que apunte a la actividad existente
        val notificationIntent = Intent(this, NewRouteActivity::class.java).apply {
            // Flags importantes para reutilizar la actividad existente
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            
            // Agregar acción especial para identificar que viene de la notificación
            action = "com.example.stepbystep.NOTIFICATION_CLICK"
            
            // Opcional: agregar datos actuales como extra para recuperar estado
            putExtra("FROM_NOTIFICATION", true)
        }

        // Crear PendingIntent con flag de actualización
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Crear y devolver la notificación
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Grabando ruta")
            .setContentText(getNotificationText())
            .setSmallIcon(R.drawable.ic_location_active)
            .setContentIntent(pendingIntent)
            .setOngoing(true) // Evita que el usuario pueda descartar la notificación
            .build()
    }
    
    /**
     * Genera el texto para la notificación basado en el estado actual del tracking.
     */
    private fun getNotificationText(): String {
        val distanceStr = StringFormatUtils.formatDistance(distanceInMeters / 1000.0) // Convertir a km
        val timeStr = StringFormatUtils.formatTime(elapsedTimeMillis)
        
        val status = if (isPaused) "Pausado" else "Activo"
        
        return "$status | Distancia: $distanceStr | Tiempo: $timeStr"
    }
    
    /**
     * Inicia el rastreo de ubicación y prepara el estado inicial.
     */
    fun startLocationTracking() {
        Log.d(TAG, "[SERVICE] Iniciando rastreo de ubicación...")
        
        if (isTracking) {
            Log.d(TAG, "[SERVICE] ✗ El servicio ya está rastreando, ignorando llamada")
            return
        }
        
        // Inicializar o reiniciar variables de seguimiento
        isTracking = true
        isPaused = false
        distanceInMeters = 0f
        lastLocation = null
        startTimeMillis = SystemClock.elapsedRealtime()
        elapsedTimeMillis = 0L
        currentElevation = 0.0
        elevationGain = 0.0
        lastProcessedElevation = 0.0
        routePoints.clear()
        
        Log.d(TAG, "[SERVICE] Variables reiniciadas: dist=0, elev=0, gain=0, tiempo=0")
        
        // Adquirir wakelock para mantener la CPU activa
        if (backgroundEnabled) {
            acquireWakeLock()
        }
        
        // Comenzar a recibir actualizaciones de ubicación
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
            
            // Iniciar actualizaciones periódicas de tiempo
            handler.post(timeUpdateRunnable)
            
            Log.d(TAG, "[SERVICE] ✓ Actualizaciones de ubicación solicitadas correctamente")
        } catch (e: SecurityException) {
            Log.e(TAG, "[SERVICE] ✗ Error al solicitar actualizaciones de ubicación", e)
            stopSelf()
        }
    }
    
    /**
     * Procesa una nueva ubicación recibida del proveedor de ubicación.
     */
    private fun handleNewLocation(location: Location) {
        Log.d(TAG, "[TRACKING] Nueva ubicación recibida: ${location.latitude}, ${location.longitude}, alt=${location.altitude}")
        
        // Calcular distancia si tenemos una ubicación previa
        val lastLoc = lastLocation
        if (lastLoc != null) {
            val segmentDistance = lastLoc.distanceTo(location)
            Log.d(TAG, "[TRACKING] Segmento calculado: $segmentDistance m (umbral: 0.2m)")
            
            // Reducir el umbral de 1.0f a 0.2f metros para capturar movimientos más pequeños
            if (segmentDistance > 0.0f) { // Más de 20 centímetros
                distanceInMeters += segmentDistance
                // Añadir a la lista de puntos
                routePoints.add(LatLng(location.latitude, location.longitude))
                Log.d(TAG, "[TRACKING] ✓ Punto aceptado! Distancia total acumulada: $distanceInMeters m (${distanceInMeters/1000} km)")
                
                // Procesar cambios en elevación
                currentElevation = location.altitude
                Log.d(TAG, "[TRACKING] Elevación actual: $currentElevation m, última: $lastProcessedElevation m")
                
                // Calcular ganancia de elevación (solo cambios positivos)
                if (lastProcessedElevation != 0.0) {
                    val elevationDifference = currentElevation - lastProcessedElevation
                    Log.d(TAG, "[TRACKING] Diferencia elevación: $elevationDifference m")
                    
                    if (elevationDifference > 0.2) {
                        elevationGain += elevationDifference
                        Log.d(TAG, "[TRACKING] ✓ Ganancia elevación añadida: +$elevationDifference m, total: $elevationGain m")
                    } else {
                        Log.d(TAG, "[TRACKING] ✗ Diferencia elevación ignorada (< 0.2m)")
                    }
                }
                
                lastProcessedElevation = currentElevation
            } else {
                Log.d(TAG, "[TRACKING] ✗ Punto ignorado por distancia insuficiente: $segmentDistance m")
            }
        } else {
            // Primera ubicación
            currentElevation = location.altitude
            lastProcessedElevation = currentElevation
            Log.d(TAG, "[TRACKING] Punto inicial registrado. Elevación inicial: $currentElevation m")
        }
        
        // Guardar la ubicación actual para la próxima comparación
        lastLocation = location
        
        // Enviar broadcast con la nueva ubicación y estadísticas actualizadas
        sendLocationBroadcast(location)
    }

    /**
     * Envía un broadcast con la ubicación actual y estadísticas para que la actividad se actualice.
     */
    private fun sendLocationBroadcast(location: Location) {
        val intent = Intent(ACTION_LOCATION_BROADCAST).apply {
            // Hacer el intent EXPLÍCITO - especificar el destinatario
            setPackage(packageName)
            
            // Añadir los extras como antes
            putExtra(EXTRA_LOCATION, location)
            putExtra(EXTRA_DISTANCE, distanceInMeters)
            putExtra(EXTRA_TIME, elapsedTimeMillis)
            putExtra(EXTRA_ELEVATION, currentElevation)
            putExtra(EXTRA_ELEVATION_GAIN, elevationGain)
        }
        
        Log.d(TAG, "[BROADCAST] Enviando: dist=$distanceInMeters m, tiempo=$elapsedTimeMillis ms, elev=$currentElevation m, gain=$elevationGain m")
        
        // Enviar el broadcast explícito
        sendBroadcast(intent)
    }
    
    /**
     * Actualiza el tiempo transcurrido basado en el tiempo de inicio y pausas.
     */
    private fun updateElapsedTime() {
        if (!isPaused) {
            elapsedTimeMillis = SystemClock.elapsedRealtime() - startTimeMillis
        }
    }
    
    /**
     * Detiene el rastreo de ubicación y libera recursos.
     */
    fun stopLocationTracking() {
        Log.d(TAG, "stopLocationTracking")
        
        if (!isTracking) return
        
        // Detener actualizaciones de ubicación
        fusedLocationClient.removeLocationUpdates(locationCallback)
        
        // Detener actualizaciones periódicas de tiempo
        handler.removeCallbacks(timeUpdateRunnable)
        
        // Actualizar estado
        isTracking = false
        isPaused = false
        
        // Liberar wakelock
        releaseWakeLock()
        
        // Si estamos en primer plano, detener el servicio
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(STOP_FOREGROUND_REMOVE)
        } else {
            @Suppress("DEPRECATION")
            stopForeground(true)
        }
    }
    
    /**
     * Pausa el rastreo de ubicación (mantiene el servicio activo).
     */
    fun pauseTracking() {
        Log.d(TAG, "pauseTracking")
        
        if (isTracking && !isPaused) {
            isPaused = true
            pauseStartTimeMillis = SystemClock.elapsedRealtime()
            
            // Actualizar notificación para mostrar estado pausado
            updateNotification()
            
            // Pausar actualizaciones de ubicación para ahorrar batería
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
    
    /**
     * Reanuda el rastreo de ubicación después de una pausa.
     */
    fun resumeTracking() {
        Log.d(TAG, "resumeTracking")
        
        if (isTracking && isPaused) {
            isPaused = false
            
            // Ajustar el tiempo de inicio para considerar la pausa
            startTimeMillis += (SystemClock.elapsedRealtime() - pauseStartTimeMillis)
            
            // Reanudar actualizaciones de ubicación
            try {
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
                
                // Reiniciar actualizaciones periódicas de tiempo
                handler.post(timeUpdateRunnable)
                
            } catch (e: SecurityException) {
                Log.e(TAG, "Error resuming location updates", e)
            }
            
            // Actualizar notificación
            updateNotification()
        }
    }
    
    /**
     * Ajusta los parámetros de rastreo cuando la app cambia entre primer plano y segundo plano.
     *
     * @param inBackground true si la app está en segundo plano, false si está en primer plano
     */
    fun adjustForBackgroundMode(inBackground: Boolean) {
        if (!isTracking) return

        Log.d(TAG, "[SERVICE] Ajustando modo de rastreo: ${if (inBackground) "segundo plano" else "primer plano"}")
        
        // Actualizar flag de modo
        backgroundEnabled = inBackground
        
        // Si estamos en segundo plano, asegurar que el WakeLock está adquirido
        if (inBackground) {
            acquireWakeLock()
        }
        
        // Usar el mismo intervalo de 2 segundos tanto en primer como en segundo plano
        val updateInterval = 2000L  // 2 segundos en ambos modos
        val minUpdateInterval = 1000L  // 1 segundo como intervalo mínimo
        
        try {
            // Cancelar solicitud actual
            fusedLocationClient.removeLocationUpdates(locationCallback)
            
            // Crear nueva solicitud con intervalos ajustados
            locationRequest = LocationRequest.Builder(updateInterval)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setMinUpdateIntervalMillis(minUpdateInterval)
                .build()
            
            // Solicitar actualizaciones con la nueva configuración
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
            
            // Actualizar la notificación para mostrar el estado actual
            updateNotification()
            
            Log.d(TAG, "[SERVICE] ✓ Ajustado a modo ${if (inBackground) "SEGUNDO PLANO" else "PRIMER PLANO"}: " +
                  "intervalo=${updateInterval}ms, min=${minUpdateInterval}ms")
        } catch (e: SecurityException) {
            Log.e(TAG, "[SERVICE] ❌ Error al solicitar actualizaciones ajustadas: ${e.message}")
        }
    }

    /**
     * Actualiza la notificación con la información actual.
     */
    private fun updateNotification() {
        if (isTracking) {
            val notification = createNotification()
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }
    
    // Métodos de acceso para que la actividad obtenga datos del servicio
    fun isTracking(): Boolean = isTracking
    fun isPaused(): Boolean = isPaused
    fun getDistance(): Float = distanceInMeters
    fun getElapsedTime(): Long = elapsedTimeMillis
    fun getCurrentElevation(): Double = currentElevation
    fun getElevationGain(): Double = elevationGain
    fun getRoutePoints(): List<LatLng> = routePoints.toList()

    /**
     * Crea la solicitud de ubicación optimizada para seguimiento de ruta.
     */
    private fun createLocationRequest() {
        // Usar el constructor con intervalo en milisegundos
        locationRequest = LocationRequest.Builder(2000) // 2 segundos como intervalo base
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setMinUpdateIntervalMillis(1000) // Mínimo 1 segundo entre actualizaciones
            .setMaxUpdateDelayMillis(3000)   // Máximo 3 segundos de retraso
            .build()
    }
}