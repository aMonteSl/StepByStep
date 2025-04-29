package com.example.stepbystep.data.local

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    var athleteName: String
        get() = prefs.getString(KEY_ATHLETE_NAME, "Atleta") ?: "Atleta"
        set(value) = prefs.edit().putString(KEY_ATHLETE_NAME, value).apply()
        
    var athleteDescription: String
        get() = prefs.getString(KEY_ATHLETE_DESCRIPTION, "") ?: ""
        set(value) = prefs.edit().putString(KEY_ATHLETE_DESCRIPTION, value).apply()
    
    companion object {
        private const val PREFS_NAME = "user_preferences"
        private const val KEY_ATHLETE_NAME = "athlete_name"
        private const val KEY_ATHLETE_DESCRIPTION = "athlete_description"
    }
}