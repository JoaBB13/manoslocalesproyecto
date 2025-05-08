package com.undef.manoslocalesproyecto

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailProductScreen(
    productId: Int,
    productos: List<Producto>,
    onBack: () -> Unit
) {
    val producto = productos.find { it.id == productId }

    if (producto == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Producto no encontrado", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onBack) {
                Text("Volver")
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Detalle del Producto", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nombre: ${producto.nombre}", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Descripción: ${producto.descripcion}")
                Text("Categoría: ${producto.categoria}")
                Text("Ciudad: ${producto.ciudad}")
                Text("Vendedor: ${producto.vendedor}")
                Text("Stock: ${producto.stock} unidades")
                Text("Fecha de publicación: ${producto.fechaPublicacion}")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBack) {
            Text("Volver")
        }
    }
}

