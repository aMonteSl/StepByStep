package com.example.stepbystep.ui.saveroute

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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

    private val imagePicker = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedImageUri = it
            binding.routeImagePreview.setImageURI(selectedImageUri)
            binding.routeImagePreview.visibility = View.VISIBLE
            binding.btnSelectImage.text = "Cambiar foto"
            saveImageToInternalStorage()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openImagePicker()
        } else {
            Toast.makeText(this, "Se necesita permiso para acceder a las imágenes", Toast.LENGTH_SHORT).show()
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
            
            viewModel.setRouteData(distance, duration, elevationGain, elevation, points, altitudes)
            
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
            
            if (points.isNotEmpty()) {
                // Draw route on map
                map.addPolyline(
                    PolylineOptions()
                        .addAll(points)
                        .width(8f)
                        .color(getColor(com.example.stepbystep.R.color.turquoise))
                )
                
                // Zoom to fit the entire route
                val boundsBuilder = LatLngBounds.Builder()
                points.forEach { boundsBuilder.include(it) }
                
                // Add padding to the bounds
                val bounds = boundsBuilder.build()
                val padding = resources.getDimensionPixelSize(com.example.stepbystep.R.dimen.map_padding)
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    openImagePicker()
                }
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) -> {
                    // Mostrar diálogo explicativo y luego solicitar permiso
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Permiso necesario")
                        .setMessage("Se necesita acceso a la galería para seleccionar fotos para tus rutas.")
                        .setPositiveButton("Conceder") { _, _ ->
                            // Solicita el permiso DIRECTAMENTE usando ActivityCompat
                            ActivityCompat.requestPermissions(
                                this,
                                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                                STORAGE_PERMISSION_CODE
                            )
                        }
                        .setNegativeButton("Cancelar", null)
                        .show()
                }
                else -> {
                    // Solicita el permiso DIRECTAMENTE usando ActivityCompat
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        STORAGE_PERMISSION_CODE
                    )
                }
            }
        } else {
            openImagePicker()
        }
    }

    private fun openImagePicker() {
        try {
            // Intenta usar un intent explícito para abrir la galería
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        } catch (e: Exception) {
            // Si falla, intenta con un intent más genérico
            try {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
            } catch (e: Exception) {
                Toast.makeText(this, "No se pudo abrir el selector de imágenes: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
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
                
                Toast.makeText(this, "Imagen guardada correctamente", Toast.LENGTH_SHORT).show()
                
            } catch (e: Exception) {
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
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker()
            } else {
                Toast.makeText(
                    this,
                    "Permiso denegado. No se puede seleccionar una foto.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                selectedImageUri = uri
                binding.routeImagePreview.setImageURI(selectedImageUri)
                binding.routeImagePreview.visibility = View.VISIBLE
                binding.btnSelectImage.text = "Cambiar foto"
                saveImageToInternalStorage()
            }
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