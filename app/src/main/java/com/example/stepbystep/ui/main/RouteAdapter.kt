package com.example.stepbystep.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbystep.databinding.ItemRouteBinding
import com.example.stepbystep.domain.model.Route

class RouteAdapter(
    private var routes: List<Route> = emptyList(),
    private val onClick: (Route) -> Unit
) : RecyclerView.Adapter<RouteAdapter.RouteViewHolder>() {

    class RouteViewHolder(val binding: ItemRouteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val binding = ItemRouteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RouteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val route = routes[position]
        holder.binding.route = route
        holder.binding.executePendingBindings()
        
        // Set click listener on the whole item
        holder.itemView.setOnClickListener { onClick(route) }
    }

    override fun getItemCount(): Int = routes.size

    fun updateRoutes(newRoutes: List<Route>) {
        routes = newRoutes
        notifyDataSetChanged()
    }
}
