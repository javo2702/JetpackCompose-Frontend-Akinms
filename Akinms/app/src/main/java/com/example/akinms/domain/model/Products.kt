package com.example.akinms.domain.model

data class Products(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val descuento: Double,
    val stock: Int,
    val img: String,
)
