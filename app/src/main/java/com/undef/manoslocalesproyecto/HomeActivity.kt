package com.undef.manoslocalesproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Datos simulados por ahora (más adelante vendrán desde un servidor)
        val vendedores = listOf(
            Vendedor(1, "Ana Mercado", "Panadería", "Productos artesanales hechos con amor."),
            Vendedor(2, "Carlos Huerta", "Verduras", "Huerta agroecológica fresca."),
            Vendedor(3, "Lucía Artesanías", "Artesanías", "Tejidos y decoraciones regionales.")
        )

        setContent {
            ManoslocalesproyectoTheme {
                HomeScreen(vendedores)
            }
        }
    }
}
