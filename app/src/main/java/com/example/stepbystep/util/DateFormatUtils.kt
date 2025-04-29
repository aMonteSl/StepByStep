package com.example.stepbystep.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtils {
  @JvmStatic
  fun formatDate(dateStr: String?): String {
    if (dateStr == null) return ""  // O cualquier valor predeterminado que prefieras
    
    return try {
      val parser    = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
      val date      = parser.parse(dateStr) ?: return dateStr
      val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
      formatter.format(date)
    } catch (e: Exception) {
      dateStr
    }
  }
}
