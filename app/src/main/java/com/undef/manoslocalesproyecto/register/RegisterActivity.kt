package com.undef.manoslocalesproyecto.register

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme
import com.undef.manoslocalesproyecto.UserPreferencesLogin
import com.undef.manoslocalesproyecto.UserRepository
import com.undef.manoslocalesproyecto.UserViewModel

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear el ViewModel con dependencias
        val repository = UserRepository(applicationContext)
        val preferences = UserPreferencesLogin(applicationContext)
        val viewModel = UserViewModel(repository, preferences)

        setContent {
            ManoslocalesproyectoTheme {
                RegisterScreen(viewModel)
            }
        }
    }
}
