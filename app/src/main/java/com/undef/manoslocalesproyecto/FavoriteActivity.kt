package com.undef.manoslocalesproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme

class FavoriteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val favoritos = ProductoFavoritos.favoritos

        setContent {
            ManoslocalesproyectoTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "favorites") {
                    composable("favorites") {
                        FavoriteScreen(
                            favoritos = favoritos,
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
                            productos = favoritos,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}


