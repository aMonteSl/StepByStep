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

/**
 * Actividad principal de la aplicación. Muestra la lista de rutas guardadas
 * y permite al usuario navegar a las diferentes funcionalidades de la app:
 * crear nuevas rutas, ver detalles, importar GPX o acceder al perfil.
 */
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val viewModel: MainViewModel by viewModels {
    MainViewModelFactory(this) // Pasamos el contexto en lugar de intentar obtener la instancia del repositorio directamente
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

    // Observar cambios en la lista de rutas y actualizar la UI
    viewModel.routes.observe(this) { list ->
        adapter.updateRoutes(list)
        binding.layoutEmptyState.isVisible = list.isEmpty()
    }

    // Configurar botón de acción flotante para añadir nuevas rutas
    binding.fabAddRoute.setOnClickListener {
        startActivity(Intent(this, NewRouteActivity::class.java))
    }
  }

  /**
   * Infla el menú de la barra de herramientas con las opciones disponibles
   */
  override fun onCreateOptionsMenu(menu: Menu) =
    menuInflater.inflate(R.menu.toolbar_menu, menu).let { true }

  /**
   * Maneja las selecciones de elementos del menú de la barra de herramientas
   */
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

  /**
   * Muestra un diálogo de confirmación antes de eliminar una ruta
   * para evitar eliminaciones accidentales
   */
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
