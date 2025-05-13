package com.undef.manoslocalesproyecto

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import java.util.*
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionsScreen(
    onBack: () -> Unit,
    viewModel: QuestionsViewModel = viewModel()
) {
    //val sharedViewModel: SharedAppViewModel = viewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()//corrutina
    val snackbarHostState = remember { SnackbarHostState() }

    var titulo by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Enviar Consulta") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }//muestra mensajes temporales
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = mensaje,
                onValueChange = { mensaje = it },
                label = { Text("Mensaje") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                maxLines = 5
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    if (titulo.isBlank() || mensaje.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Complete todos los campos")
                        }
                        return@Button
                    }

                    scope.launch {//ejecuta en una corrutina
                        try {
                            if (viewModel.sendEmail(context, titulo, mensaje)) {//devuelve true si se abrio la app de correo
                                snackbarHostState.showSnackbar("Correo enviado")
                            } else {
                                snackbarHostState.showSnackbar("No hay app de correo")//devuelve false
                            }
                        } catch (e: Exception) {
                            snackbarHostState.showSnackbar("Error: ${e.localizedMessage}")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar consulta")
            }
        }
    }
}