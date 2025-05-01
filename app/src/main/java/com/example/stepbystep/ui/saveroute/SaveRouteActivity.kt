package com.example.stepbystep.ui.saveroute

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.stepbystep.R
import com.example.stepbystep.databinding.ActivitySaveRouteBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.stepbystep.ui.main.MainActivity
import com.example.stepbystep.util.MapUtils.configureMapStyle
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Actividad para guardar una ruta finalizada.
 * 
 * Permite al usuario:
 * - Ver una previsualización de la ruta en un mapa
 * - Introducir un nombre y descripción para la ruta
 * - Seleccionar una imagen para asociarla con la ruta
 * - Guardar todos los datos en la base de datos local
 * 
 * Recibe los datos de la ruta a través del Intent que la inicia,
 * ya sea desde NewRouteActivity o ImportRouteActivity.
 */
class SaveRouteActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveRouteBinding
    private val viewModel: SaveRouteViewModel by viewModels {
        SaveRouteViewModelFactory(this)
    }
    
    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null
    private var selectedImageUri: Uri? = null

    /**
     * Contrato para solicitar permisos de almacenamiento.
     * Maneja el resultado de la solicitud y muestra diálogos
     * explicativos según corresponda.
     */
    private val storagePermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permiso concedido, abrir selector de imágenes
            openImagePicker()
        } else {
            // Determinar qué permiso se denegó basado en la versión de Android
            val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Manifest.permission.READ_MEDIA_IMAGES
            } else {
                Manifest.permission.READ_EXTERNAL_STORAGE
            }
            
            // Permiso denegado
            if (!shouldShowRequestPermissionRationale(permission)) {
                // El usuario marcó "No preguntar de nuevo"
                MaterialAlertDialogBuilder(this)
                    .setTitle("Permiso requerido")
                    .setMessage("Has denegado permanentemente el acceso a la galería. Necesitas habilitarlo manualmente en la configuración de la aplicación.")
                    .setPositiveButton("Ir a Configuración") { _, _ ->
                        val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
            // No mostrar Toast aquí, ya que puede interferir con la experiencia del usuario
        }
    }

    /**
     * Contrato para seleccionar imágenes de la galería.
     * Maneja el resultado de la selección y actualiza la UI.
     */
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            binding.routeImagePreview.setImageURI(selectedImageUri)
            binding.routeImagePreview.visibility = View.VISIBLE
            binding.btnSelectImage.text = "Cambiar foto"
            saveImageToInternalStorage()
        }
    }

    /**
     * Inicializa la actividad, configura las vistas y carga los datos
     * de la ruta desde el intent que la inició.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        
        // Configurar la barra de herramientas
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        
        // Configurar el mapa
        mapView = binding.mapPreview
        mapView.onCreate(savedInstanceState)
        
        // Obtener datos de la ruta desde el intent
        intent.extras?.let { bundle ->
            val distance = bundle.getDouble("distance", 0.0)
            val duration = bundle.getLong("duration", 0L)
            val elevationGain = bundle.getDouble("elevationGain", 0.0)
            val elevation = bundle.getDouble("elevation", 0.0)
            val points = bundle.getParcelableArrayList<LatLng>("points") ?: emptyList()
            val altitudes = bundle.getDoubleArray("altitudes")
            
            // Comprobar si es una ruta importada
            val isImported = bundle.getBoolean("imported", false)
            if (isImported) {
                // Para rutas importadas, ya tenemos nombre y descripción
                val name = bundle.getString("name", "")
                val description = bundle.getString("description", "")
                
                // Añadir log para verificar los valores
                Log.d("SaveRoute", "Imported route name: '$name', description: '$description'")
                
                // Establecer nombre y descripción en el ViewModel
                viewModel.routeName.value = name
                viewModel.routeDescription.value = description
            }
            
            viewModel.setRouteData(
                distance, 
                duration, 
                elevationGain, 
                elevation, 
                points, 
                altitudes,
                isImported
            )
            
            // Configurar el mapa una vez que tenemos los datos
            setupMap(points)
        }
        
        // Configurar listeners de los botones
        binding.btnSave.setOnClickListener {
            viewModel.saveRoute()
            // Navegar a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        binding.btnDiscard.setOnClickListener {
            // Volver a NewRouteActivity directamente
            finish()
        }

        // Configurar el botón para seleccionar imagen
        binding.btnSelectImage.setOnClickListener {
            checkAndRequestStoragePermission()
        }
    }
    
    /**
     * Configura el mapa con la visualización de la ruta.
     * Crea una línea que representa el recorrido y ajusta la cámara
     * para mostrar la ruta completa.
     * 
     * @param points Lista de puntos geográficos que componen la ruta
     */
    private fun setupMap(points: List<LatLng>) {
        mapView.getMapAsync { map ->
            googleMap = map
            
            // Configurar estilo del mapa según modo día/noche
            googleMap?.configureMapStyle(this)
            
            // Deshabilitar interacciones ya que es solo una vista previa
            googleMap?.uiSettings?.apply {
                setAllGesturesEnabled(false)
                isMapToolbarEnabled = false
            }
            
            if (points.isNotEmpty()) {
                // Determinar el color de la ruta según el modo del tema
                val isNightMode = (resources.configuration.uiMode and 
                                  Configuration.UI_MODE_NIGHT_MASK) == 
                                  Configuration.UI_MODE_NIGHT_YES
                
                // Usar turquesa para modo oscuro, oxford para modo claro
                val routeColor = if (isNightMode) {
                    ContextCompat.getColor(this, R.color.turquoise)
                } else {
                    ContextCompat.getColor(this, R.color.oxford)
                }
                
                // Añadir la polilínea de la ruta
                googleMap?.addPolyline(
                    PolylineOptions()
                        .addAll(points)
                        .width(12f)
                        .color(routeColor)
                        .jointType(JointType.ROUND)
                )
                
                // Ajustar zoom para mostrar toda la ruta
                val boundsBuilder = LatLngBounds.Builder()
                points.forEach { boundsBuilder.include(it) }
                val bounds = boundsBuilder.build()
                
                // Añadir padding alrededor de la ruta
                val padding = resources.getDimensionPixelSize(R.dimen.map_padding)
                val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                googleMap?.moveCamera(cameraUpdate)
            }
        }
    }
    
    /**
     * Muestra un diálogo de confirmación antes de descartar la ruta.
     * Previene la pérdida accidental de datos.
     */
    private fun confirmDiscard() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.discard_route_title))
            .setMessage(getString(R.string.discard_route_message))
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(getString(R.string.discard)) { _, _ ->
                // Volver a NewRouteActivity
                finish()
            }
            .show()
    }
    
    /**
     * Verifica y solicita el permiso adecuado para acceder a la galería,
     * según la versión de Android del dispositivo.
     */
    private fun checkAndRequestStoragePermission() {
        // Determinar qué permiso solicitar basado en la versión de Android
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                    openImagePicker()
                }
                shouldShowRequestPermissionRationale(permission) -> {
                    // Mostrar explicación de por qué se necesita el permiso
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Permiso necesario")
                        .setMessage("Se necesita acceso a la galería para seleccionar fotos para tus rutas.")
                        .setPositiveButton("Solicitar permiso") { _, _ ->
                            // Lanzar solicitud de permiso con el permiso adecuado
                            storagePermissionRequest.launch(permission)
                        }
                        .setNegativeButton("Cancelar", null)
                        .show()
                }
                else -> {
                    // Primera vez que se solicita el permiso o "No preguntar de nuevo" marcado
                    // Esto debería mostrar el diálogo del sistema
                    storagePermissionRequest.launch(permission)
                }
            }
        } else {
            // En versiones anteriores a Marshmallow, no se necesita permiso en tiempo de ejecución
            openImagePicker()
        }
    }

    /**
     * Abre el selector de imágenes del sistema.
     */
    private fun openImagePicker() {
        pickImage.launch("image/*")
    }

    /**
     * Guarda la imagen seleccionada en el almacenamiento interno de la aplicación
     * y actualiza el ViewModel con la ruta del archivo.
     */
    private fun saveImageToInternalStorage() {
        selectedImageUri?.let { uri ->
            try {
                val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
                val imageName = "route_image_$timestamp.jpg"
                val imageDir = File(filesDir, "route_images")
                
                // Crear directorio si no existe
                if (!imageDir.exists()) {
                    imageDir.mkdirs()
                }
                
                val destinationFile = File(imageDir, imageName)
                val inputStream = contentResolver.openInputStream(uri)
                val outputStream = FileOutputStream(destinationFile)
                
                inputStream?.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                
                // Guardar la ruta de la imagen en el ViewModel
                val imagePath = destinationFile.absolutePath
                viewModel.setImagePath(imagePath)
                
                // Verificar que el path se ha establecido correctamente
                Log.d("SaveRoute", "Image path set in ViewModel: ${viewModel.imagePath.value}")
                
                // Añadir un log para depuración
                Log.d("SaveRoute", "Image saved to: $imagePath")
                
                Toast.makeText(this, "Imagen guardada correctamente", Toast.LENGTH_SHORT).show()
                
            } catch (e: Exception) {
                Log.e("SaveRoute", "Error saving image: ${e.message}", e)
                Toast.makeText(this, "Error al guardar la imagen: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    /**
     * Maneja las acciones de los elementos del menú.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                confirmDiscard()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    // Métodos del ciclo de vida del mapa
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
        super.onSaveInstanceState(outState)  // Pasar el Bundle a la clase padre
        mapView.onSaveInstanceState(outState)
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 100
        private const val PICK_IMAGE_REQUEST_CODE = 101
    }
}