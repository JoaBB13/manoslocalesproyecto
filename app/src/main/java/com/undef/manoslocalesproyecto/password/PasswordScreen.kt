package com.undef.manoslocalesproyecto.password

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.undef.manoslocalesproyecto.UserViewModel
import com.undef.manoslocalesproyecto.UserRepository
import com.undef.manoslocalesproyecto.UserPreferencesLogin

@Composable
fun EnterEmailScreen(onNext: (String) -> Unit, viewModel: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Recuperar contraseña", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                error = null
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = error != null
        )

        if (error != null) {
            Text(text = error!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.checkUserExists(email) { exists ->
                    if (exists) {
                        onNext(email)
                    } else {
                        error = "No se encontró una cuenta con este correo"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar código")
        }
    }
}



