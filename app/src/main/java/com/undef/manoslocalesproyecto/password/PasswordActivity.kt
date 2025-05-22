package com.undef.manoslocalesproyecto.password

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class PasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "email") {
                composable("email") {
                    EnterEmailScreen { email ->
                        // podés guardar el email si querés
                        navController.navigate("code")
                    }
                }
                composable("code") {
                    EnterCodeScreen {
                        navController.navigate("new_password")
                    }
                }
                composable("new_password") {
                    SetNewPasswordScreen {
                        // volver a login o mostrar mensaje
                        finish() // cierra la actividad
                    }
                }
            }
        }
    }
}