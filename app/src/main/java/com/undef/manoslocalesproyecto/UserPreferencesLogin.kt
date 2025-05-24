package com.undef.manoslocalesproyecto

import android.content.Context
import android.content.SharedPreferences

class UserPreferencesLogin(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserEmail(email: String) {
        prefs.edit().putString("email", email).apply()
    }

    fun getUserEmail(): String? {
        return prefs.getString("email", null)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}
