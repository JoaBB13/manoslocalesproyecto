package com.undef.manoslocalesproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme

class ProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Por ahora usamos productos ficticios para probar
        val productosDeEjemplo = listOf(
            Producto(1, "Pan casero", "Panadería", "Hecho con masa madre", "Rosario", vendedorId = 1),
            Producto(2, "Zapallo orgánico", "Verduras", "Cosechado hoy", "Santa Fe", vendedorId = 2)
        )

        setContent {
            ManoslocalesproyectoTheme {
                ProductScreen(productos = productosDeEjemplo)
            }
        }
    }
}
