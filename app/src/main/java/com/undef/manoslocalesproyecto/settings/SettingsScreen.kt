package com.undef.manoslocalesproyecto.settings

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(context: Context, onBack: () -> Unit) {
    val scope = rememberCoroutineScope()
    val userPreferences = remember { UserPreferences(context) }//clase que maneja el almacenamiento en AdataStore

    val busquedaPor by userPreferences.busquedaPor.collectAsState(initial = "todos(ciudad,categoria,vendedor)")
    val tiempoNotificacion by userPreferences.tiempoNotificacion.collectAsState(initial = 60)//collectAsState: Convierte el flujo de datos en un estado observable por Compose.

    var busquedaSeleccionada by remember { mutableStateOf(busquedaPor) }
    var tiempoSeleccionado by remember { mutableStateOf(tiempoNotificacion.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        TopAppBar(
            title = { Text("Configuración") },
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                }
            }
        )

        Text("Preferencias de Búsqueda", style = MaterialTheme.typography.titleLarge)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("ciudad", "categoria", "vendedor", "Todos(ciudad, categoria, vendedor)").forEach { opcion ->//selector de filtro de busqueda
                Button(
                    onClick = {
                        busquedaSeleccionada = opcion
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (busquedaSeleccionada == opcion) MaterialTheme.colorScheme.primary else Color.LightGray,
                        contentColor = if (busquedaSeleccionada == opcion) Color.White else Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(opcion.replaceFirstChar { it.uppercaseChar() })
                }
            }

            Button(
                onClick = {
                    scope.launch {
                        userPreferences.setBusquedaPor(busquedaSeleccionada)
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Guardar filtro")
            }
        }

        Divider()

        Text("Tiempo de notificaciones (minutos)", style = MaterialTheme.typography.titleLarge)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = tiempoSeleccionado,
                onValueChange = { tiempoSeleccionado = it },
                label = { Text("Minutos") },
                singleLine = true,
                modifier = Modifier.width(120.dp)
            )
        }

        Button(
            onClick = {
                val minutos = tiempoSeleccionado.toIntOrNull() ?: 60
                scope.launch {//lanza una corrutina: suspender su ejecucion en un punto especifico y retomarla mas tarde, manteniendo su estado interno
                    userPreferences.setTiempoNotificacion(minutos)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Guardar tiempo")
        }

        Text("Valor actual: $tiempoNotificacion minutos")
    }
}





