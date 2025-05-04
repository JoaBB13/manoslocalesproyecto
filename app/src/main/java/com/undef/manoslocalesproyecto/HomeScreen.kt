package com.undef.manoslocalesproyecto

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme

data class Vendedor(
    val id: Int,
    val nombre: String,
    val rubro: String,
    val descripcion: String
)

@Composable
fun HomeScreen(vendedores: List<Vendedor>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(vendedores) { vendedor ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(vendedor.nombre, style = MaterialTheme.typography.titleMedium)
                    Text(vendedor.rubro, style = MaterialTheme.typography.bodyMedium)
                    Text(vendedor.descripcion, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val vendedoresDeEjemplo = listOf(
        Vendedor(1, "Ana Mercado", "Panadería", "Productos artesanales hechos."),
        Vendedor(2, "Carlos Huerta", "Verduras", "Huerta agroecológica fresca."),
        Vendedor(3, "Lucía Artesanías", "Artesanías", "Tejidos y decoraciones regionales.")
    )

    ManoslocalesproyectoTheme {
        HomeScreen(vendedores = vendedoresDeEjemplo)
    }
}
