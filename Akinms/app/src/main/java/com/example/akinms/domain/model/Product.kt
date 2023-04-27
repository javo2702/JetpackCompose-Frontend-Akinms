package com.example.akinms.domain.model

data class Product(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val cantidad: Int,
    val img: String,
)
