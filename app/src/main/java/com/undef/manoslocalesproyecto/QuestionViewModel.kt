package com.undef.manoslocalesproyecto

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {
    fun hasEmailApp(context: Context): Boolean {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
        }
        return intent.resolveActivity(context.packageManager) != null
    }

    fun sendEmail(context: Context, title: String, message: String): Boolean {
        if (!hasEmailApp(context)) return false

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:desarrollador@ejemplo.com")
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, message)
        }
        context.startActivity(intent)
        return true
    }
}

/*¿Qué es ViewModel?
Es una clase diseñada para:

Almacenar datos de la UI: Mantiene información que debe sobrevivir a cambios de configuración (ej: rotación de pantalla).

Separar la lógica de la vista: La UI (Activity/Fragment/Composable) solo muestra datos, mientras el ViewModel los procesa.

Comunicarse con otras capas: Coordina con repositorios o casos de uso para obtener datos.*/