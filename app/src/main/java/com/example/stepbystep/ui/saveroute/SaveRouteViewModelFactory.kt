package com.example.stepbystep.ui.saveroute

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SaveRouteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaveRouteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SaveRouteViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}