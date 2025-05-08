package com.undef.manoslocalesproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme

class ProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productosDeEjemplo = listOf(
            Producto(1, "Pan Casero", "Panadería", "Pan artesanal horneado a leña", "Córdoba", "Pedro", 10, "2025-05-01"),
            Producto(2, "Lechuga Fresca", "Verduras", "Cultivo sin pesticidas", "Rosario", "Gonzalo", 25, "2025-04-30"),
            Producto(3, "Dulce de Leche", "Lácteos", "Dulce artesanal de tambo local", "Mendoza", "Alonso", 15, "2025-04-28"),
            Producto(4, "Frutillas", "Frutas", "Frutillas recién cosechadas", "Salta", "Roberto", 20, "2025-04-25"),
            Producto(5, "Cartera de Cuero", "Artesanías", "Hecha a mano", "Buenos Aires", "Guille", 5, "2025-05-02"),
            Producto(6, "Conserva de Tomate", "Conservas", "Sin conservantes artificiales", "Tucumán", "Francisco", 18, "2025-05-01"),
            Producto(7, "Pan Integral", "Panadería", "Con semillas y harina orgánica", "La Plata", "Lucía", 12, "2025-04-27"),
            Producto(8, "Zanahorias", "Verduras", "Recién cosechadas", "Santa Fe", "Martina", 30, "2025-04-29"),
            Producto(9, "Yogur Natural", "Lácteos", "De producción casera", "San Juan", "Miguel", 8, "2025-05-01"),
            Producto(10, "Manzanas", "Frutas", "De productores locales", "Neuquén", "María", 22, "2025-04-24"),
            Producto(11, "Alfajores Artesanales", "Panadería", "Rellenos de dulce de leche", "Córdoba", "Pedro", 14, "2025-05-03"),
            Producto(12, "Tomates Cherry", "Verduras", "Cultivo hidropónico", "San Luis", "Ramiro", 16, "2025-04-30"),
            Producto(13, "Queso de Cabra", "Lácteos", "Producto gourmet artesanal", "Chaco", "Paula", 6, "2025-05-02"),
            Producto(14, "Peras", "Frutas", "Jugosas y dulces", "Río Negro", "Luis", 19, "2025-04-26"),
            Producto(15, "Pulsera de Macramé", "Artesanías", "Colores personalizados", "Salta", "Nina", 9, "2025-04-28"),
            Producto(16, "Conserva de Berenjena", "Conservas", "Ideal para picadas", "Entre Ríos", "Iván", 13, "2025-04-25"),
            Producto(17, "Pan de Campo", "Panadería", "Receta tradicional", "Santa Cruz", "Julieta", 11, "2025-05-01"),
            Producto(18, "Acelga Orgánica", "Verduras", "Sin agroquímicos", "Jujuy", "Tomás", 21, "2025-04-29"),
            Producto(19, "Ricota Fresca", "Lácteos", "Ideal para rellenos", "Corrientes", "Laura", 7, "2025-04-27")
        )

        setContent {
            ManoslocalesproyectoTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "products") {
                    composable("products") {
                        ProductScreen(
                            productos = productosDeEjemplo,
                            onProductClick = { id ->
                                navController.navigate("detail/$id")
                            }
                        )
                    }
                    composable(
                        "detail/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
                        DetailProductScreen(
                            productId = productId,
                            productos = productosDeEjemplo,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

