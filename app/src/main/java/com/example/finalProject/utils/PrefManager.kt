package com.example.finalProject.utils

import android.content.Context
import android.content.SharedPreferences

class PrefManager internal constructor(val context: Context) {
    fun saveLoginDetails(email: String?, name: String?, userId: Int) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Email", email)
        editor.putString("Name", name)
        editor.putInt("User ID", userId)
        editor.commit()
    }

    val userId: Int?
        get() {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE)
            return sharedPreferences.getInt("User ID", 0)
        }

    val name: String?
        get() {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE)
            return sharedPreferences.getString("Name", "")
        }
}