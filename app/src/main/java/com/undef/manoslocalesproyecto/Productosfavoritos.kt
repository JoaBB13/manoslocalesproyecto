package com.undef.manoslocalesproyecto

object ProductoFavoritos {//crea una instancia global en toda la app
    val favoritos = mutableListOf<Producto>()//lista modificable de objetos Producto
    val alertasActivas = mutableSetOf<Int>() // IDs de productos con alertas activas

    fun activarAlerta(productId: Int) = alertasActivas.add(productId)
    fun desactivarAlerta(productId: Int) = alertasActivas.remove(productId)
    fun tieneAlerta(productId: Int) = alertasActivas.contains(productId)

    fun desactivarTodasLasAlertas() = alertasActivas.clear()
}
