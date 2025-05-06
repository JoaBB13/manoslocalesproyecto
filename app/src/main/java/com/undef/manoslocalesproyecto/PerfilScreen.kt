package com.undef.manoslocalesproyecto

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

@Composable
fun PerfilScreen(
    nombreInicial: String,
    emailInicial: String,
    onGuardar: (String, String) -> Unit
) {
    var nombre by remember { mutableStateOf(nombreInicial) }
    var email by remember { mutableStateOf(emailInicial) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mi Perfil", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        val context = LocalContext.current

        Button(
            onClick = {
                onGuardar(nombre, email)
                val intent = Intent(context, ProductActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar cambios")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    ManoslocalesproyectoTheme {
        PerfilScreen(
            nombreInicial = "Juan Usuario",
            emailInicial = "juan@email.com"
        ) { _, _ -> }
    }
}
