package com.undef.manoslocalesproyecto

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserSessionManager(private val context: Context) {
    companion object {
        val EMAIL_KEY = stringPreferencesKey("email")
    }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[EMAIL_KEY] = email
        }
    }

    suspend fun getEmail(): String? {
        return context.dataStore.data.map { prefs -> prefs[EMAIL_KEY] }.first()
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
