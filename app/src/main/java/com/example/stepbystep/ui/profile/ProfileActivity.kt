package com.example.stepbystep.ui.profile

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.stepbystep.R
import com.example.stepbystep.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Configurar la toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        
        // Configurar el texto inicial del botón
        binding.btnEditProfile.text = getString(R.string.edit)
        
        // Observar cambios en el modo de edición y actualizar el ícono y texto
        viewModel.isEditMode.observe(this) { isEditMode ->
            // Actualizar el ícono según el modo
            binding.btnEditProfile.setIconResource(
                if (isEditMode) R.drawable.ic_save else R.drawable.ic_edit
            )
            
            // Actualizar el texto según el modo
            binding.btnEditProfile.text = getString(
                if (isEditMode) R.string.save else R.string.edit
            )
        }

        // Configurar el botón de editar/guardar
        binding.btnEditProfile.setOnClickListener {
            if (viewModel.isEditMode.value == true) {
                // Guardar cambios
                val name = binding.etAthleteName.text.toString()
                val description = binding.etAthleteDescription.text.toString()
                viewModel.updateProfile(name, description)
            } else {
                // Entrar en modo edición
                viewModel.toggleEditMode()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}