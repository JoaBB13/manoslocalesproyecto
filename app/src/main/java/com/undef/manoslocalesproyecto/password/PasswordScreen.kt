package com.undef.manoslocalesproyecto.password

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)//me permite usar TopAPPBar
@Composable
fun EnterEmailScreen(onNext: (String) -> Unit) {
    var email by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Recuperar contraseña", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        Button(onClick = { onNext(email) },
                modifier = Modifier.fillMaxWidth()) {
            Text("Enviar código")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EnterEmailScreenPreview() {
    EnterEmailScreen(onNext = { /* no hace nada en preview */ })
}
