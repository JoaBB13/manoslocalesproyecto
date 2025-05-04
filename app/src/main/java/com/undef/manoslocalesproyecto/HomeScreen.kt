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
    val rubros = listOf("Panadería", "Verduras", "Artesanías", "Frutas", "Lácteos", "Conservas")
    val descripciones = listOf(
        "Productos artesanales hechos en casa.",
        "Huerta agroecológica fresca.",
        "Tejidos y decoraciones regionales.",
        "Frutas de estación recién cosechadas.",
        "Lácteos frescos de producción local.",
        "Conservas caseras sin aditivos."
    )
    val nombres = listOf(
        "Ana Mercado", "Carlos Huerta", "Lucía Artesanías", "Pedro Frutos",
        "Sofía Dulces", "Martín Hortaliza", "Elena Pan", "Juan Queso",
        "Rosa Tejidos", "Luis Frutas", "Marta Sazón", "Diego Campo",
        "Paula Feria", "Nico Agro", "Carla Artesana", "Esteban Panadero",
        "Laura Eco", "Marcos Mercado", "Cecilia Orgánica", "Damián Verde"
    )

    val vendedoresDeEjemplo = List(20) { index ->
        Vendedor(
            id = index + 1,
            nombre = nombres[index % nombres.size],
            rubro = rubros[index % rubros.size],
            descripcion = descripciones[index % descripciones.size]
        )
    }

    ManoslocalesproyectoTheme {
        HomeScreen(vendedores = vendedoresDeEjemplo)
    }
}

