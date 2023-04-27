package com.example.akinms.data.source.remote.dto.producto

data class Producto(
    val descripcion: String,
    val descuento: Double,
    val idProducto: Int,
    val img: String,
    val nombre: String,
    val precio: Double,
    val stock: Int
)