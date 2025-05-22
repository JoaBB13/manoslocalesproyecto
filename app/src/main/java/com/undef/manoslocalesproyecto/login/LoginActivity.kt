package com.undef.manoslocalesproyecto.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManoslocalesproyectoTheme {
                LoginScreen()
            }
        }
    }
}