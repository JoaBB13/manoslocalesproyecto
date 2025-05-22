package com.undef.manoslocalesproyecto.product

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
import com.undef.manoslocalesproyecto.favorites.FavoriteActivity
import com.undef.manoslocalesproyecto.favorites.ProductoFavoritos
import com.undef.manoslocalesproyecto.settings.UserPreferences

data class Producto(//modelo de datos de productos
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
    onProductClick: (Int) -> Unit,//callback
    onAlertsClick: () -> Unit,//callback
    onSettingsClick: () -> Unit,
    onConsultasClick: () -> Unit,
    onPerfilClick: () -> Unit
) {
    //val sharedViewModel: SharedAppViewModel = viewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userPreferences = remember { UserPreferences(context) }//lee el filtro guardado en DataStore

    var query by remember { mutableStateOf("") }//se usa en el buscador
    var buscando by remember { mutableStateOf(false) }
    var menuExpanded by remember { mutableStateOf(false) }
    var favoritos by remember { mutableStateOf(ProductoFavoritos.favoritos.map { it.id }.toSet()) }//Usa un set para evitar duplicados
    var filtroGuardado by remember { mutableStateOf("todos") }

    // Observar el valor de filtro desde DataStore
    val filtroFlow by userPreferences.busquedaPor.collectAsState(initial = "todos")
    LaunchedEffect(filtroFlow) {
        filtroGuardado = filtroFlow
    }

    val resultadosBusqueda = productos.filter {
        val texto = query.lowercase()//texto ingresado query y usa lowercase para normalizar
        when (filtroGuardado) {
            "ciudad" -> it.ciudad.lowercase().contains(texto)
            "categoria" -> it.categoria.lowercase().contains(texto)
            "vendedor" -> it.vendedor.lowercase().contains(texto)
            else -> {
                it.nombre.lowercase().contains(texto) ||
                        it.categoria.lowercase().contains(texto) ||
                        it.ciudad.lowercase().contains(texto) ||
                        it.vendedor.lowercase().contains(texto)
            }
        }
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
                label = { Text("Buscar por $filtroGuardado") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                singleLine = true
            )//campo de busqueda, actualiza query reactivamente al escribir

            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Menú")
                }

                DropdownMenu( //muestra opciones adicionales al hacer click en el menu
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Mi Perfil") },
                        onClick = {
                            menuExpanded = false
                            onPerfilClick()
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
                            onSettingsClick()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Consultas") },
                        onClick = {
                            menuExpanded = false
                            onConsultasClick()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Alertas") },
                        onClick = {
                            menuExpanded = false
                            onAlertsClick()
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

        LazyColumn { //renderiza solo los elementos visibles
            val lista = if (buscando) resultadosBusqueda else productos
            items(lista) { producto -> //metodo extension para listas reactivas
                Box(modifier = Modifier.padding(vertical = 8.dp)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onProductClick(producto.id) },//al hacer click, pasa al detalle
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
                                //remover de favoritos
                                ProductoFavoritos.favoritos.removeAll { it.id == producto.id }
                                favoritos = favoritos - producto.id
                            } else {
                                //agregar a favoritos
                                if (ProductoFavoritos.favoritos.none { it.id == producto.id }) {
                                    ProductoFavoritos.favoritos.add(producto)
                                }
                                favoritos = favoritos + producto.id//actualzia estado local de favoritos
                            }
                        },//al hacer click, agrega o quita de favoritos
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = if (favoritos.contains(producto.id)) Icons.Filled.Star else Icons.Filled.StarBorder,
                            contentDescription = "Favorito",
                            tint = if (favoritos.contains(producto.id)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )//cambia el icono visualmente
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