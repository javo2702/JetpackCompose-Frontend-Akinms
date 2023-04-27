package com.example.akinms.data.source.remote.dto.pedido

data class Producto(
    var idproducto: Int,
    val descripcion: String? = "",
    val descuento: Double? = 0.0,
    val img: String? = "",
    val nombre: String? = "",
    val precio: Double? = 0.0,
    val stock: Int? = 0
)