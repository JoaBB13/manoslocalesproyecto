package com.undef.manoslocalesproyecto

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FavoriteScreen(
    favoritos: List<Producto>,//Lista de productos favoritos
    onProductClick: (Int) -> Unit
) {
    val context = LocalContext.current
    var actualizacion by remember { mutableStateOf(0) } // Variable de estado que fuerza la actualización de la UI al cambiar

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                context.startActivity(Intent(context, ProductActivity::class.java))
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver a productos")
            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Mis Favoritos",
                style = MaterialTheme.typography.titleLarge
            )
        }//Boton de retroceso y titulo centrado

        Spacer(modifier = Modifier.height(16.dp))

        if (favoritos.isEmpty()) {
            Text("No hay productos favoritos.")
        } else {
            LazyRow(//Muestra items en scroll horizontal
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(favoritos) { producto ->//renderiza cada item visible
                    Card(
                        modifier = Modifier
                            .width(250.dp)
                            .height(180.dp)
                            .clickable { onProductClick(producto.id) },//ejecuta onProductClick al tocar la tarjeta
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(Alignment.TopStart)
                            ) {
                                Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                                Text("Categoría: ${producto.categoria}", style = MaterialTheme.typography.bodySmall)
                                Text("Ciudad: ${producto.ciudad}", style = MaterialTheme.typography.bodySmall)
                                Text("Vendedor: ${producto.vendedor}", style = MaterialTheme.typography.bodySmall)
                            }

                            IconButton(
                                onClick = {
                                    if (ProductoFavoritos.tieneAlerta(producto.id)) {
                                        ProductoFavoritos.desactivarAlerta(producto.id)
                                    } else {
                                        ProductoFavoritos.activarAlerta(producto.id)
                                    }
                                    actualizacion++ // fuerza recomposición
                                },
                                modifier = Modifier.align(Alignment.TopEnd)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "Activar/Desactivar alerta",
                                    tint = if (ProductoFavoritos.tieneAlerta(producto.id)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}





/*@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    val ejemplo = listOf(
        Producto(1, "Pan Casero", "Panadería", "Pan artesanal", "Córdoba", "Pedro"),
        Producto(2, "Dulce de Leche", "Lácteos", "Dulce artesanal", "Mendoza", "Alonso")
    )

    ManoslocalesproyectoTheme {
        FavoriteScreen(ejemplo)
    }
}*/
