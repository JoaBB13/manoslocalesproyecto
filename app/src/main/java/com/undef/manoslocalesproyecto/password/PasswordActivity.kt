package com.undef.manoslocalesproyecto.password

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.undef.manoslocalesproyecto.UserViewModel
import com.undef.manoslocalesproyecto.UserRepository
import com.undef.manoslocalesproyecto.UserPreferencesLogin
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

class PasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userRepository = UserRepository(applicationContext)
        val userPreferences = UserPreferencesLogin(applicationContext)
        val viewModel = UserViewModel(userRepository, userPreferences)

        setContent {
            ManoslocalesproyectoTheme {
                val navController = rememberNavController()
                var email by remember { mutableStateOf("") }

                NavHost(navController = navController, startDestination = "email") {
                    composable("email") {
                        EnterEmailScreen(onNext = {
                            email = it
                            navController.navigate("code")
                        }, viewModel = viewModel)
                    }
                    composable("code") {
                        EnterCodeScreen {
                            navController.navigate("new_password")
                        }
                    }
                    composable("new_password") {
                        SetNewPasswordScreen(email = email, onPasswordChanged = {
                            finish() // cerrar actividad al guardar nueva contraseña
                        }, viewModel = viewModel)
                    }
                }
            }
        }
    }
}
