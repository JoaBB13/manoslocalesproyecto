package com.undef.manoslocalesproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManoslocalesproyectoTheme {
                RegisterScreen()
            }
        }
    }
}
