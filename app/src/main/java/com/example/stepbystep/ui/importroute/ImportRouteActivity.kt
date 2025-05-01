package com.example.stepbystep.ui.importroute

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.stepbystep.R
import com.example.stepbystep.databinding.ActivityImportRouteBinding
import com.example.stepbystep.ui.saveroute.SaveRouteActivity
import com.example.stepbystep.util.GpxParser
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class ImportRouteActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityImportRouteBinding
    private var selectedGpxUri: Uri? = null
    
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedGpxUri = it
            
            // Show the selected file name
            val filename = getFileNameFromUri(it)
            binding.tvSelectedFile.text = filename
            binding.btnImport.isEnabled = true
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImportRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        
        // Verificar si la actividad se abrió desde un intent externo
        handleIncomingIntent(intent)
        
        // Setup buttons
        binding.btnSelectFile.setOnClickListener {
            openFilePicker()
        }
        
        binding.btnImport.setOnClickListener {
            importSelectedFile()
        }
        
        // Initially disable the import button until a file is selected
        binding.btnImport.isEnabled = false
    }
    
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIncomingIntent(intent)
    }
    
    private fun handleIncomingIntent(intent: Intent) {
        // Verificar si el intent tiene la acción VIEW
        if (intent.action == Intent.ACTION_VIEW) {
            // Obtener la URI del archivo
            val uri = intent.data
            if (uri != null) {
                Log.d("ImportRoute", "Received URI: $uri")
                Log.d("ImportRoute", "MIME Type: ${contentResolver.getType(uri)}")
                
                // Si viene de una app externa, procesar directamente
                if (intent.flags and Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY == 0) {
                    if (isValidGpxFile(uri)) {
                        selectedGpxUri = uri
                        // Procesar inmediatamente e ir a SaveRouteActivity
                        processGpxAndNavigate(uri)
                        return
                    } else {
                        Toast.makeText(this, 
                            R.string.invalid_gpx_file, 
                            Toast.LENGTH_SHORT).show()
                    }
                }
                
                // Si no se procesó directamente, configurar la UI normal
                selectedGpxUri = uri
                val filename = getFileNameFromUri(uri)
                binding.tvSelectedFile.text = filename
                binding.btnImport.isEnabled = true
                
                Toast.makeText(this, 
                    "Archivo GPX cargado. Pulsa 'Importar' para continuar.", 
                    Toast.LENGTH_LONG).show()
                
                Log.d("ImportRoute", "Received external file: $filename")
            }
        }
    }
    
    private fun processGpxAndNavigate(uri: Uri) {
        // Parse GPX file
        val gpxData = GpxParser.parse(this, uri) ?: run {
            Toast.makeText(this, R.string.gpx_parse_error, Toast.LENGTH_SHORT).show()
            return
        }
        
        // Calculate route statistics
        val (distance, elevationGain, averageElevation) = GpxParser.calculateRouteStats(gpxData.points)
        
        // Convert to format SaveRouteActivity expects
        val points = gpxData.points.map { LatLng(it.latitude, it.longitude) }
        val altitudes = gpxData.points.map { it.elevation }.toDoubleArray()
        val duration = if (gpxData.points.size >= 2) {
            gpxData.points.last().timestamp - gpxData.points.first().timestamp
        } else {
            0L
        }
        
        // Start SaveRouteActivity with imported data
        val intent = Intent(this, SaveRouteActivity::class.java).apply {
            putExtra("distance", distance)
            putExtra("duration", duration)
            putExtra("elevationGain", elevationGain)
            putExtra("elevation", averageElevation)
            putParcelableArrayListExtra("points", ArrayList(points))
            putExtra("altitudes", altitudes)
            putExtra("name", gpxData.name)
            putExtra("description", gpxData.description)
            putExtra("imported", true)
        }
        
        startActivity(intent)
        finish()
    }
    
    private fun openFilePicker() {
        getContent.launch("application/gpx+xml")
    }
    
    private fun importSelectedFile() {
        val uri = selectedGpxUri ?: run {
            Toast.makeText(this, R.string.no_gpx_selected, Toast.LENGTH_SHORT).show()
            return
        }
        
        // Validate GPX file
        if (!isValidGpxFile(uri)) {
            Toast.makeText(this, R.string.invalid_gpx_file, Toast.LENGTH_SHORT).show()
            return
        }
        
        // Parse GPX file
        val gpxData = GpxParser.parse(this, uri)
        
        if (gpxData == null) {
            Toast.makeText(this, R.string.gpx_parse_error, Toast.LENGTH_SHORT).show()
            return
        }
        
        // Calculate route statistics
        val (distance, elevationGain, averageElevation) = GpxParser.calculateRouteStats(gpxData.points)
        
        // Convert to format SaveRouteActivity expects
        val points = gpxData.points.map { LatLng(it.latitude, it.longitude) }
        val altitudes = gpxData.points.map { it.elevation }.toDoubleArray()
        val duration = if (gpxData.points.size >= 2) {
            gpxData.points.last().timestamp - gpxData.points.first().timestamp
        } else {
            0L
        }
        
        // Start SaveRouteActivity with imported data
        val intent = Intent(this, SaveRouteActivity::class.java).apply {
            putExtra("distance", distance)
            putExtra("duration", duration)
            putExtra("elevationGain", elevationGain)
            putExtra("elevation", averageElevation)
            putParcelableArrayListExtra("points", ArrayList(points))
            putExtra("altitudes", altitudes)
            putExtra("name", gpxData.name)
            putExtra("description", gpxData.description)
            putExtra("imported", true)
        }
        
        startActivity(intent)
        finish()
    }
    
    private fun getFileNameFromUri(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        
        return cursor?.use {
            val nameIndex = it.getColumnIndex("_display_name")
            if (nameIndex != -1 && it.moveToFirst()) {
                it.getString(nameIndex)
            } else {
                uri.lastPathSegment ?: "archivo.gpx"
            }
        } ?: uri.lastPathSegment ?: "archivo.gpx"
    }
    
    private fun isValidGpxFile(uri: Uri): Boolean {
        try {
            // Primero intentar verificar por tipo MIME
            val mimeType = contentResolver.getType(uri)
            if (mimeType != null && 
                (mimeType == "application/gpx+xml" || 
                 mimeType == "application/xml" || 
                 mimeType == "text/xml")) {
                return true
            }
            
            // Si el tipo MIME no es concluyente, verificar el contenido
            contentResolver.openInputStream(uri)?.use { inputStream ->
                val buffer = ByteArray(256) // Leer más bytes para una mejor detección
                val bytesRead = inputStream.read(buffer)
                if (bytesRead > 0) {
                    val content = String(buffer, 0, bytesRead)
                    // Verificar si el contenido parece un GPX
                    return content.contains("<gpx") && 
                           (content.contains("http://www.topografix.com/GPX") || 
                            content.contains("xmlns:gpx"))
                }
            }
            
            // Si llegamos aquí, verificar la extensión del archivo
            val fileName = getFileNameFromUri(uri)
            return fileName.toLowerCase(Locale.ROOT).endsWith(".gpx")
            
        } catch (e: Exception) {
            Log.e("ImportRoute", "Error validating GPX file", e)
        }
        return false
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}