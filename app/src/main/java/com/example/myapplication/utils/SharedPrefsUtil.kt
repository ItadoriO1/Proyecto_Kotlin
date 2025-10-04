package com.example.myapplication.utils

import android.content.Context
import androidx.core.content.edit
import com.example.myapplication.model.Role

object SharedPrefsUtil {

    fun savePreference(context: Context, userId: String, rol: Role){
        val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString("userId", userId)
            putString("role", rol.toString())
        }
    }

    fun clearPreference(context: Context){
        val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            clear()
        }
    }

    fun getPreference(context: Context): Map<String, String> {
        val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", "")
        val rol = sharedPreferences.getString("role", "")

        return if (userId.isNullOrEmpty() || rol.isNullOrEmpty()) {
            emptyMap()
        } else {
            mapOf(
                "userId" to userId,
                "role" to rol
            )
        }
    }
}