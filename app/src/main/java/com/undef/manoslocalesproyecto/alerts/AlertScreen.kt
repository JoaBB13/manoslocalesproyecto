package com.undef.manoslocalesproyecto.alerts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.undef.manoslocalesproyecto.favorites.ProductoFavoritos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertScreen(onBack: () -> Unit) {
    // Estado para recomponer la pantalla cuando se desactivan alertas
    var actualizacion by remember { mutableStateOf(0) }

    // Obtener productos con alerta activa
    val alertas = ProductoFavoritos.favoritos.filter {
        ProductoFavoritos.tieneAlerta(it.id)
    }//Obtiene solo los favoritos que tienen alertas activas usando tieneAlerta() del singleton ProductoFavoritos

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        TopAppBar(
            title = { Text("Alertas") },
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                }
            }
        )

        Text("Alertas activas", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        if (alertas.isEmpty()) {
            Text("No hay alertas activas.")
        } else {
            LazyColumn {
                items(alertas) { producto ->//transforma la lista alertas en componentes UI
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Novedades del producto favorito")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                ProductoFavoritos.desactivarTodasLasAlertas()
                actualizacion++ // Fuerza recomposición, asegura que UI(Interfaz de usuario) se actualice
            }) {
                Text("Desactivar todas las alertas")
            }
        }


    }
}

