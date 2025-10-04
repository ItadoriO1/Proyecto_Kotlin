package com.example.myapplication.model

import java.util.Locale

enum class DayOfWeek(val displayName: String) {
    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miércoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sábado"),
    DOMINGO("Domingo");

    companion object {
        fun fromString(dayStr: String): DayOfWeek? {
            return entries.find { it.name.equals(dayStr, ignoreCase = true) || it.displayName.equals(dayStr, ignoreCase = true) }
        }
    }
}
