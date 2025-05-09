package com.undef.manoslocalesproyecto

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.undef.manoslocalesproyecto.FavoriteActivity
import com.undef.manoslocalesproyecto.PerfilActivity
import com.undef.manoslocalesproyecto.ProductoFavoritos

data class Producto(
    val id: Int,
    val nombre: String,
    val categoria: String,
    val descripcion: String,
    val ciudad: String,
    val vendedor: String,
    val stock: Int,
    val fechaPublicacion: String
)

@Composable
fun ProductScreen(
    productos: List<Producto>,
    onProductClick: (Int) -> Unit,
    onAlertsClick: () -> Unit//mejor usar on click antes que navcontroller
) {
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    var buscando by remember { mutableStateOf(false) }
    var menuExpanded by remember { mutableStateOf(false) }
    var favoritos by remember { mutableStateOf(ProductoFavoritos.favoritos.map { it.id }.toSet()) }

    val resultadosBusqueda = productos.filter {
        val texto = query.lowercase()
        it.nombre.lowercase().contains(texto) ||
                it.categoria.lowercase().contains(texto) ||
                it.ciudad.lowercase().contains(texto) ||
                it.vendedor.lowercase().contains(texto)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    buscando = it.isNotBlank()
                },
                label = { Text("Buscar por categoría, ciudad o vendedor") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                singleLine = true
            )

            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Menú")
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Mi Perfil") },
                        onClick = {
                            menuExpanded = false
                            context.startActivity(Intent(context, PerfilActivity::class.java))
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Favoritos") },
                        onClick = {
                            menuExpanded = false
                            context.startActivity(Intent(context, FavoriteActivity::class.java))
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Configuración") },
                        onClick = {
                            menuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Consultas") },
                        onClick = {
                            menuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Alertas") },
                        onClick = {
                            menuExpanded = false
                            onAlertsClick()//esto en vez de un navcontroller es mejor
                        }
                    )
                }
            }
        }

        Text(
            text = if (buscando) "Resultados de búsqueda" else "Todos los productos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        LazyColumn {
            val lista = if (buscando) resultadosBusqueda else productos
            items(lista) { producto ->
                Box(modifier = Modifier.padding(vertical = 8.dp)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onProductClick(producto.id) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Categoría: ${producto.categoria}", style = MaterialTheme.typography.bodyMedium)
                            Text("Ciudad: ${producto.ciudad}", style = MaterialTheme.typography.bodySmall)
                            Text("Vendedor: ${producto.vendedor}", style = MaterialTheme.typography.bodySmall)
                        }
                    }

                    IconButton(
                        onClick = {
                            if (favoritos.contains(producto.id)) {
                                ProductoFavoritos.favoritos.removeAll { it.id == producto.id }
                                favoritos = favoritos - producto.id
                            } else {
                                if (ProductoFavoritos.favoritos.none { it.id == producto.id }) {
                                    ProductoFavoritos.favoritos.add(producto)
                                }
                                favoritos = favoritos + producto.id
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = if (favoritos.contains(producto.id)) Icons.Filled.Star else Icons.Filled.StarBorder,
                            contentDescription = "Favorito",
                            tint = if (favoritos.contains(producto.id)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )
                    }
                }
            }
        }
    }
}




/*@Preview(showBackground = true, showSystemUi = true)
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
*/