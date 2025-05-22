package com.undef.manoslocalesproyecto.settings

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensión de contexto
val Context.dataStore by preferencesDataStore(name = "user_settings")//userSettings es donde se guardaran las preferencias

// Claves para acceder a los valores guardados
object UserPreferencesKeys {
    val BUSQUEDA_POR = stringPreferencesKey("busqueda_por") // "ciudad", "categoria", "vendedor", "todos"
    val TIEMPO_NOTIFICACION = intPreferencesKey("tiempo_notificacion") // en minutos
}

// Clase para acceder/modificar preferencias
class UserPreferences(private val context: Context) {

    val busquedaPor: Flow<String> = context.dataStore.data//flujo que emite los valores actuales de las preferencias
        .map { it[UserPreferencesKeys.BUSQUEDA_POR] ?: "todos" }//transforma el flujo para extraer el valor de BUSQUEDA_POR

    val tiempoNotificacion: Flow<Int> = context.dataStore.data
        .map { it[UserPreferencesKeys.TIEMPO_NOTIFICACION] ?: 60 }

    suspend fun setBusquedaPor(valor: String) {//suspend indica que la funcion es una corrutina
        context.dataStore.edit { it[UserPreferencesKeys.BUSQUEDA_POR] = valor }//dataStore.edit modifica el valor asociado a la clave
    }

    suspend fun setTiempoNotificacion(minutos: Int) {
        context.dataStore.edit { it[UserPreferencesKeys.TIEMPO_NOTIFICACION] = minutos }
    }
}

