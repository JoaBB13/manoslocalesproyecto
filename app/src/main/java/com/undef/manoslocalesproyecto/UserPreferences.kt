package com.undef.manoslocalesproyecto

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensión de contexto
val Context.dataStore by preferencesDataStore(name = "user_settings")

// Claves
object UserPreferencesKeys {
    val BUSQUEDA_POR = stringPreferencesKey("busqueda_por") // "ciudad", "categoria", "vendedor", "todos"
    val TIEMPO_NOTIFICACION = intPreferencesKey("tiempo_notificacion") // en minutos
}

// Clase para acceder/modificar preferencias
class UserPreferences(private val context: Context) {

    val busquedaPor: Flow<String> = context.dataStore.data
        .map { it[UserPreferencesKeys.BUSQUEDA_POR] ?: "todos" }

    val tiempoNotificacion: Flow<Int> = context.dataStore.data
        .map { it[UserPreferencesKeys.TIEMPO_NOTIFICACION] ?: 60 }

    suspend fun setBusquedaPor(valor: String) {
        context.dataStore.edit { it[UserPreferencesKeys.BUSQUEDA_POR] = valor }
    }

    suspend fun setTiempoNotificacion(minutos: Int) {
        context.dataStore.edit { it[UserPreferencesKeys.TIEMPO_NOTIFICACION] = minutos }
    }
}

