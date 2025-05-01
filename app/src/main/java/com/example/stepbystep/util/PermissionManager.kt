package com.example.stepbystep.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Gestor de permisos de ubicación.
 * 
 * Se encarga de verificar y solicitar los permisos necesarios para
 * el rastreo de ubicación en primer y segundo plano.
 *
 * @param activity Actividad desde la cual se solicitan los permisos
 */
class LocationPermissionManager(private val activity: AppCompatActivity) {

    /**
     * Launcher para solicitar permisos de ubicación en primer plano
     */
    private val requestForegroundLocationPermissions: ActivityResultLauncher<Array<String>> by lazy {
        activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val allGranted = permissions.entries.all { it.value }
            
            if (allGranted) {
                permissionCallback?.onForegroundLocationPermissionGranted()
                
                // Verificar si necesitamos permisos en segundo plano
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && !hasBackgroundLocationPermission()) {
                    activity.window.decorView.post {
                        showBackgroundLocationPermissionRationale()
                    }
                }
            } else {
                showLocationPermissionDeniedDialog()
                permissionCallback?.onPermissionDenied()
            }
        }
    }
    
    /**
     * Launcher para solicitar permisos de ubicación en segundo plano
     */
    private val requestBackgroundLocationPermission: ActivityResultLauncher<String> by lazy {
        activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                permissionCallback?.onBackgroundLocationPermissionGranted()
            } else {
                showBackgroundLocationPermissionDeniedDialog()
                permissionCallback?.onBackgroundLocationPermissionDenied()
            }
        }
    }

    /**
     * Interface para notificar resultados de permisos
     */
    interface PermissionCallback {
        fun onForegroundLocationPermissionGranted()
        fun onBackgroundLocationPermissionGranted()
        fun onBackgroundLocationPermissionDenied()
        fun onPermissionDenied()
    }
    
    private var permissionCallback: PermissionCallback? = null
    
    /**
     * Establece el callback para recibir notificaciones sobre cambios en permisos
     */
    fun setPermissionCallback(callback: PermissionCallback) {
        permissionCallback = callback
    }
    
    /**
     * Verifica y solicita los permisos de ubicación necesarios
     * @return true si ya tiene todos los permisos necesarios
     */
    fun checkAndRequestLocationPermissions(): Boolean {
        if (!hasForegroundLocationPermissions()) {
            requestForegroundLocationPermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            return false
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && !hasBackgroundLocationPermission()) {
            showBackgroundLocationPermissionRationale()
            return false
        }
        
        return true
    }
    
    /**
     * Verifica si la aplicación tiene permisos de ubicación en primer plano
     */
    fun hasForegroundLocationPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * Verifica si la aplicación tiene permisos de ubicación en segundo plano
     */
    fun hasBackgroundLocationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContextCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true  // En versiones anteriores a Android 10, no se necesita este permiso específico
        }
    }
    
    /**
     * Solicita permisos de ubicación en primer plano
     */
    fun requestLocationPermissions() {
        requestForegroundLocationPermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
    
    /**
     * Muestra un diálogo explicando la necesidad de los permisos de ubicación en segundo plano
     */
    fun showBackgroundLocationPermissionRationale() {
        MaterialAlertDialogBuilder(activity)
            .setTitle("Permiso de ubicación en segundo plano")
            .setMessage("Para seguir registrando tu ruta incluso cuando la app está en segundo plano o la pantalla está apagada, necesitamos permiso para acceder a tu ubicación en segundo plano.")
            .setPositiveButton("Conceder permiso") { _, _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    // En Android 11 o superior, debemos enviar al usuario a la configuración
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", activity.packageName, null)
                    intent.data = uri
                    activity.startActivity(intent)
                    
                    Toast.makeText(
                        activity,
                        "Ve a Permisos > Ubicación > y selecciona 'Permitir todo el tiempo'",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                    // En Android 10, podemos solicitar directamente
                    requestBackgroundLocationPermission.launch(
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    )
                }
            }
            .setNegativeButton("No ahora") { dialog, _ ->
                dialog.dismiss()
                permissionCallback?.onBackgroundLocationPermissionDenied()
            }
            .show()
    }
    
    /**
     * Muestra un diálogo cuando se deniegan los permisos de ubicación en primer plano
     */
    private fun showLocationPermissionDeniedDialog() {
        MaterialAlertDialogBuilder(activity)
            .setTitle("Permisos necesarios")
            .setMessage("No podemos rastrear tu ruta sin acceso a la ubicación. Por favor, concede los permisos necesarios.")
            .setPositiveButton("Reintentar") { _, _ -> requestLocationPermissions() }
            .setNegativeButton("Cancelar", null)
            .show()
    }
    
    /**
     * Muestra un diálogo cuando se deniegan los permisos de ubicación en segundo plano
     */
    private fun showBackgroundLocationPermissionDeniedDialog() {
        MaterialAlertDialogBuilder(activity)
            .setTitle("Permiso denegado")
            .setMessage("Sin permiso de ubicación en segundo plano, la app sólo podrá rastrear tu ubicación cuando esté en primer plano. El rastreo se detendrá si la app pasa a segundo plano o la pantalla se apaga.")
            .setPositiveButton("Entendido", null)
            .show()
    }
    
    /**
     * Verifica y solicita permisos de notificaciones en Android 13+
     */
    fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    activity, 
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED) {
                
                activity.requestPermissions(
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_CODE
                )
            }
        }
    }
    
    companion object {
        private const val NOTIFICATION_PERMISSION_CODE = 100
    }
}