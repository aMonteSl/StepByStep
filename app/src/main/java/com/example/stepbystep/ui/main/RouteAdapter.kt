package com.example.stepbystep.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbystep.databinding.ItemRouteBinding
import com.example.stepbystep.domain.model.Route

/**
 * Adaptador para el RecyclerView que muestra la lista de rutas en la pantalla principal.
 * Permite manejar eventos de clic y clic largo en cada elemento.
 *
 * @param routes Lista inicial de rutas (por defecto vacía)
 * @param onClick Función que se ejecuta al hacer clic en una ruta
 * @param onLongClick Función que se ejecuta al hacer clic largo en una ruta
 */
class RouteAdapter(
    private var routes: List<Route> = emptyList(),
    private val onClick: (Route) -> Unit,
    private val onLongClick: (Route) -> Boolean = { _ -> false } // Parámetro opcional con valor por defecto
) : RecyclerView.Adapter<RouteAdapter.RouteViewHolder>() {

    /**
     * ViewHolder que contiene la vista de cada elemento de la lista.
     * Utiliza data binding para vincular los datos con el layout.
     */
    class RouteViewHolder(val binding: ItemRouteBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * Crea un nuevo ViewHolder inflando el layout de cada elemento
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val binding = ItemRouteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RouteViewHolder(binding)
    }

    /**
     * Vincula los datos de una ruta específica con su ViewHolder
     * y configura los eventos de clic
     */
    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val route = routes[position]
        holder.binding.route = route
        holder.binding.executePendingBindings()
        
        // Configurar evento de clic en todo el elemento
        holder.itemView.setOnClickListener { onClick(route) }
        
        // Configurar evento de clic largo
        holder.itemView.setOnLongClickListener { onLongClick(route) }
    }

    /**
     * Devuelve el número de elementos en la lista
     */
    override fun getItemCount(): Int = routes.size

    /**
     * Actualiza la lista de rutas y notifica al adaptador para que
     * se reflejen los cambios en la UI
     */
    fun updateRoutes(newRoutes: List<Route>) {
        routes = newRoutes
        notifyDataSetChanged()
    }
}
