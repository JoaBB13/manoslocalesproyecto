package com.undef.manoslocalesproyecto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme

class PerfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Datos simulados, podrías cargarlos de una base de datos o DataStore
        val nombre = "Juan Usuario"
        val email = "juan@email.com"

        setContent {
            ManoslocalesproyectoTheme {
                PerfilScreen(
                    nombreInicial = nombre,
                    emailInicial = email
                ) { nuevoNombre, nuevoEmail ->
                    Toast.makeText(
                        this,
                        "Datos guardados:\nNombre: $nuevoNombre\nEmail: $nuevoEmail",
                        Toast.LENGTH_LONG
                    ).show()
                    // Acá podrías guardar esos datos en tu backend o almacenamiento local
                }
            }
        }
    }
}
