package com.undef.manoslocalesproyecto

import androidx.compose.runtime.mutableStateListOf
import com.undef.manoslocalesproyecto.product.Producto

class SharedAppViewModel {
    val productos = mutableStateListOf<Producto>()
}