package com.undef.manoslocalesproyecto.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.undef.manoslocalesproyecto.R
import com.undef.manoslocalesproyecto.UserViewModel
import com.undef.manoslocalesproyecto.password.PasswordActivity
import com.undef.manoslocalesproyecto.product.ProductActivity
import com.undef.manoslocalesproyecto.register.RegisterActivity

@Composable
fun LoginScreen(viewModel: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()

    // Navegar si el login fue exitoso
    LaunchedEffect(loginState) {
        if (loginState == true) {
            val intent = Intent(context, ProductActivity::class.java)
            context.startActivity(intent)
        } else if (loginState == false) {
            Toast.makeText(context, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de la app",
            modifier = Modifier
                .size(140.dp)
                .padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.login(email.trim(), password.trim())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        TextButton(
            onClick = {
                val intent = Intent(context, RegisterActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text("¿No tenés cuenta? Registrate aquí")
        }

        TextButton(
            onClick = {
                val intent = Intent(context, PasswordActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text("¿Olvidaste tu contraseña? Cambiala aquí")
        }
    }
}
