package com.example.stepbystep.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stepbystep.R
import com.example.stepbystep.databinding.ActivityMainBinding
import com.example.stepbystep.data.repository.RouteRepository
import com.example.stepbystep.domain.model.Route
import com.example.stepbystep.ui.newroute.NewRouteActivity
import com.example.stepbystep.ui.profile.ProfileActivity
import com.example.stepbystep.ui.routedetail.RouteDetailActivity
import com.example.stepbystep.ui.importroute.ImportRouteActivity

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val viewModel: MainViewModel by viewModels {
    MainViewModelFactory(this) // Pass context instead of trying to get repository instance
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.lifecycleOwner = this
    binding.viewModel = viewModel

    setSupportActionBar(binding.toolbar)

    val adapter = RouteAdapter(
        onClick = { route ->
            // Abrir la pantalla de detalle al hacer clic en una ruta
            val intent = Intent(this, RouteDetailActivity::class.java).apply {
                putExtra(RouteDetailActivity.EXTRA_ROUTE_ID, route.id)
            }
            startActivity(intent)
        },
        onLongClick = { route ->
            // Mostrar diálogo de confirmación para eliminar
            showDeleteRouteDialog(route)
            true // Consumir el evento
        }
    )
    
    binding.rvRoutes.layoutManager = LinearLayoutManager(this)
    binding.rvRoutes.adapter = adapter

    viewModel.routes.observe(this) { list ->
        adapter.updateRoutes(list)
        binding.layoutEmptyState.isVisible = list.isEmpty()
    }

    binding.fabAddRoute.setOnClickListener {
        startActivity(Intent(this, NewRouteActivity::class.java))
    }
  }

  override fun onCreateOptionsMenu(menu: Menu) =
    menuInflater.inflate(R.menu.toolbar_menu, menu).let { true }

  override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
    R.id.menu_profile -> {
      startActivity(Intent(this, ProfileActivity::class.java))
      true
    }
    R.id.menu_import_gpx -> {
      startActivity(Intent(this, ImportRouteActivity::class.java))
      true
    }
    else -> super.onOptionsItemSelected(item)
  }

  // Añadir este método para mostrar el diálogo de confirmación
  private fun showDeleteRouteDialog(route: Route) {
    val alertDialog = AlertDialog.Builder(this)
        .setTitle("Eliminar ruta")
        .setMessage("¿Estás seguro de que deseas eliminar la ruta '${route.name}'? Esta acción no se puede deshacer.")
        .setNegativeButton("Cancelar", null)
        .setPositiveButton("Eliminar") { _, _ ->
            viewModel.deleteRoute(route)
            Toast.makeText(this, "Ruta eliminada", Toast.LENGTH_SHORT).show()
        }
        .create()
    
    alertDialog.show()
  }
}
