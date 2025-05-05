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
            Producto(1, "Pan Casero", "Panadería", "Pan artesanal horneado a leña", "Córdoba", "Pedro"),
            Producto(2, "Lechuga Fresca", "Verduras", "Cultivo sin pesticidas", "Rosario", "Gonzalo"),
            Producto(3, "Dulce de Leche", "Lácteos", "Dulce artesanal de tambo local", "Mendoza", "Alonso"),
            Producto(4, "Frutillas", "Frutas", "Frutillas recién cosechadas", "Salta", "Roberto"),
            Producto(5, "Cartera de Cuero", "Artesanías", "Hecha a mano", "Buenos Aires", "Guille"),
            Producto(6, "Conserva de Tomate", "Conservas", "Sin conservantes artificiales", "Tucumán", "Francisco"),
            Producto(7, "Pan Integral", "Panadería", "Con semillas y harina orgánica", "La Plata", "Lucía"),
            Producto(8, "Zanahorias", "Verduras", "Recién cosechadas", "Santa Fe", "Martina"),
            Producto(9, "Yogur Natural", "Lácteos", "De producción casera", "San Juan", "Miguel"),
            Producto(10, "Manzanas", "Frutas", "De productores locales", "Neuquén", "María"),
            Producto(11, "Alfajores Artesanales", "Panadería", "Rellenos de dulce de leche", "Córdoba", "Pedro"),
            Producto(12, "Tomates Cherry", "Verduras", "Cultivo hidropónico", "San Luis", "Ramiro"),
            Producto(13, "Queso de Cabra", "Lácteos", "Producto gourmet artesanal", "Chaco", "Paula"),
            Producto(14, "Peras", "Frutas", "Jugosas y dulces", "Río Negro", "Luis"),
            Producto(15, "Pulsera de Macramé", "Artesanías", "Colores personalizados", "Salta", "Nina"),
            Producto(16, "Conserva de Berenjena", "Conservas", "Ideal para picadas", "Entre Ríos", "Iván"),
            Producto(17, "Pan de Campo", "Panadería", "Receta tradicional", "Santa Cruz", "Julieta"),
            Producto(18, "Acelga Orgánica", "Verduras", "Sin agroquímicos", "Jujuy", "Tomás"),
            Producto(19, "Ricota Fresca", "Lácteos", "Ideal para rellenos", "Corrientes", "Laura"),
            )

        setContent {
            ManoslocalesproyectoTheme {
                ProductScreen(productos = productosDeEjemplo)
            }
        }
    }
}
