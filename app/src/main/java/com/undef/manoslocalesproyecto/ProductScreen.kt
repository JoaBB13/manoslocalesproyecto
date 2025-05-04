// ProductScreen.kt
package com.undef.manoslocalesproyecto

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme
import androidx.compose.ui.tooling.preview.Preview

// ✅ Acá va el data class Producto
data class Producto(
    val id: Int,
    val nombre: String,
    val categoria: String,
    val descripcion: String,
    val ciudad: String,
    val vendedorId: Int
)

@Composable
fun ProductScreen(productos: List<Producto>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(productos) { producto ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                    Text("Categoría: ${producto.categoria}", style = MaterialTheme.typography.bodyMedium)
                    Text("Ciudad: ${producto.ciudad}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductScreenPreview() {
    val productosEjemplo = listOf(
        Producto(1, "Pan Casero", "Panadería", "Pan artesanal horneado a leña", "Córdoba", vendedorId = 1),
        Producto(2, "Lechuga Fresca", "Verduras", "Cultivo sin pesticidas", "Rosario", vendedorId = 2),
        Producto(3, "Dulce de Leche", "Lácteos", "Dulce artesanal de tambo local", "Mendoza", vendedorId = 3),
        Producto(4, "Frutillas", "Frutas", "Frutillas recién cosechadas", "Salta", vendedorId = 4),
        Producto(5, "Cartera de Cuero", "Artesanías", "Hecha a mano", "Buenos Aires", vendedorId = 5),
        Producto(6, "Conserva de Tomate", "Conservas", "Sin conservantes artificiales", "Tucumán", vendedorId = 6)
    )

    ManoslocalesproyectoTheme {
        ProductScreen(productos = productosEjemplo)
    }
}
