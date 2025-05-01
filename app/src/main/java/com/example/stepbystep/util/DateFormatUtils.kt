package com.example.stepbystep.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Utilidad para el formateo de fechas en la aplicación.
 * 
 * Proporciona métodos para convertir cadenas de fecha en formatos
 * más legibles para su presentación en la interfaz de usuario.
 */
object DateFormatUtils {
  /**
   * Formatea una fecha representada como cadena de texto en formato ISO (yyyy-MM-dd)
   * a un formato más amigable para el usuario (Mes día, año).
   * 
   * @param dateStr Cadena de fecha en formato "yyyy-MM-dd" (ej: "2023-05-15")
   * @return Fecha formateada (ej: "Mayo 15, 2023") o la cadena original si no se puede parsear
   */
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
