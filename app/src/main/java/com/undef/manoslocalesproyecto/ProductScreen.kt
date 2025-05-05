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
import androidx.compose.runtime.*

// ✅ Acá va el data class Producto
data class Producto(
    val id: Int,
    val nombre: String,
    val categoria: String,
    val descripcion: String,
    val ciudad: String,
    val vendedor: String
)


@Composable
fun ProductScreen(productos: List<Producto>) {
    var query by remember { mutableStateOf("") }
    var buscando by remember { mutableStateOf(false) }

    val resultadosBusqueda = productos.filter {
        val texto = query.lowercase()
        it.nombre.lowercase().contains(texto) ||
                it.categoria.lowercase().contains(texto) ||
                it.ciudad.lowercase().contains(texto) ||
                it.vendedor.lowercase().contains(texto)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // 🟦 Campo de búsqueda
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                buscando = it.isNotBlank()
            },
            label = { Text("Buscar por categoría, ciudad o vendedor") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        // 🟪 Título de la sección
        Text(
            text = if (buscando) "Resultados de búsqueda" else "Todos los productos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // 🟩 Lista (filtrada o completa según el caso)
        LazyColumn {
            val lista = if (buscando) resultadosBusqueda else productos
            items(lista) { producto ->
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
                        Text("Vendedor: ${producto.vendedor}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductScreenPreview() {
    val productosEjemplo = listOf(
        Producto(1, "Pan Casero", "Panadería", "Pan artesanal horneado a leña", "Córdoba", "Pedro"),
        Producto(2, "Lechuga Fresca", "Verduras", "Cultivo sin pesticidas", "Rosario", "Gonzalo"),
        Producto(3, "Dulce de Leche", "Lácteos", "Dulce artesanal de tambo local", "Mendoza", "Alonso"),
        Producto(4, "Frutillas", "Frutas", "Frutillas recién cosechadas", "Salta", "Roberto"),
        Producto(5, "Cartera de Cuero", "Artesanías", "Hecha a mano", "Buenos Aires", "Guille"),
        Producto(6, "Conserva de Tomate", "Conservas", "Sin conservantes artificiales", "Tucumán", "Francisco"),
        Producto(7, "Pan Integral", "Panadería", "Con semillas y harina orgánica", "La Plata", "Lucía"),
        Producto(8, "Zanahorias", "Verduras", "Recién cosechadas", "Santa Fe", "Martina"),
        Producto(9, "Yogur Natural", "Lácteos", "De producción casera", "San Juan", "Miguel"),
        Producto(10, "Manzanas", "Frutas", "De productores locales", "Neuquén", "María"),
        Producto(11, "Alfajores Artesanales", "Panadería", "Rellenos de dulce de leche", "Córdoba", "Pedro"),
        Producto(12, "Tomates Cherry", "Verduras", "Cultivo hidropónico", "San Luis", "Ramiro"),
        Producto(13, "Queso de Cabra", "Lácteos", "Producto gourmet artesanal", "Chaco", "Paula"),
        Producto(14, "Peras", "Frutas", "Jugosas y dulces", "Río Negro", "Luis"),
        Producto(15, "Pulsera de Macramé", "Artesanías", "Colores personalizados", "Salta", "Nina"),
        Producto(16, "Conserva de Berenjena", "Conservas", "Ideal para picadas", "Entre Ríos", "Iván"),
        Producto(17, "Pan de Campo", "Panadería", "Receta tradicional", "Santa Cruz", "Julieta"),
        Producto(18, "Acelga Orgánica", "Verduras", "Sin agroquímicos", "Jujuy", "Tomás"),
        Producto(19, "Ricota Fresca", "Lácteos", "Ideal para rellenos", "Corrientes", "Laura"),
        Producto(20, "Frambuesas", "Frutas", "De cultivo patagónico", "Chubut", "Verónica")
    )

    ManoslocalesproyectoTheme {
        ProductScreen(productos = productosEjemplo)
    }
}

