package com.undef.manoslocalesproyecto

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.undef.manoslocalesproyecto.Producto
import com.undef.manoslocalesproyecto.ProductoFavoritos
import com.undef.manoslocalesproyecto.R
import com.undef.manoslocalesproyecto.ui.theme.ManoslocalesproyectoTheme

@Composable
fun FavoriteScreen() {
    val context = LocalContext.current
    val favoritos = ProductoFavoritos.favoritos

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // 🔙 Flecha para ir a ProductActivity
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
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (favoritos.isEmpty()) {
            Text("No hay productos favoritos.")
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(favoritos) { producto ->
                    Card(
                        modifier = Modifier
                            .width(250.dp)
                            .height(150.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Categoría: ${producto.categoria}", style = MaterialTheme.typography.bodySmall)
                            Text("Ciudad: ${producto.ciudad}", style = MaterialTheme.typography.bodySmall)
                            Text("Vendedor: ${producto.vendedor}", style = MaterialTheme.typography.bodySmall)
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
