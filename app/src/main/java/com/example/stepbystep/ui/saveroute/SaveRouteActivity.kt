package com.example.stepbystep.ui.saveroute

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
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

class SaveRouteActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveRouteBinding
    private val viewModel: SaveRouteViewModel by viewModels {
        SaveRouteViewModelFactory(this)
    }
    
    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null
    private var selectedImageUri: Uri? = null

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

    // Registra un nuevo ActivityResultLauncher para seleccionar imágenes
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            binding.routeImagePreview.setImageURI(selectedImageUri)
            binding.routeImagePreview.visibility = View.VISIBLE
            binding.btnSelectImage.text = "Cambiar foto"
            saveImageToInternalStorage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        
        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        
        // Setup map
        mapView = binding.mapPreview
        mapView.onCreate(savedInstanceState)
        
        // Get route data from intent
        intent.extras?.let { bundle ->
            val distance = bundle.getDouble("distance", 0.0)
            val duration = bundle.getLong("duration", 0L)
            val elevationGain = bundle.getDouble("elevationGain", 0.0)
            val elevation = bundle.getDouble("elevation", 0.0)
            val points = bundle.getParcelableArrayList<LatLng>("points") ?: emptyList()
            val altitudes = bundle.getDoubleArray("altitudes")
            
            // Check if this is an imported route
            val isImported = bundle.getBoolean("imported", false)
            if (isImported) {
                // For imported routes, we already have name and description
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
                isImported  // Pasar este valor
            )
            
            // Setup map once we have the data
            setupMap(points)
        }
        
        // Button listeners
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
    
    private fun setupMap(points: List<LatLng>) {
        mapView.getMapAsync { map ->
            googleMap = map
            
            // Usar la variable local 'map' en lugar de la propiedad 'googleMap'
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

    private fun openImagePicker() {
        pickImage.launch("image/*")
    }

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
                
                // Verificar que el path se ha establecido correctamente (nuevo código)
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
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                confirmDiscard()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    // Map lifecycle methods
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
        super.onSaveInstanceState(outState)  // Pass the Bundle to parent class
        mapView.onSaveInstanceState(outState)
    }

    companion object {
        private const val STORAGE_PERMISSION_CODE = 100
        private const val PICK_IMAGE_REQUEST_CODE = 101
    }
}