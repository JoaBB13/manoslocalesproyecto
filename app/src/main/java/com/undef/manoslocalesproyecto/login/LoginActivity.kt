package com.undef.manoslocalesproyecto.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme
import com.undef.manoslocalesproyecto.login.LoginScreen
import com.undef.manoslocalesproyecto.UserRepository
import com.undef.manoslocalesproyecto.UserPreferencesLogin
import com.undef.manoslocalesproyecto.UserViewModel

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = UserRepository(applicationContext)
        val preferences = UserPreferencesLogin(applicationContext)
        val viewModel = UserViewModel(repository, preferences)

        setContent {
            ManoslocalesproyectoTheme {
                LoginScreen(viewModel)
            }
        }
    }
}
