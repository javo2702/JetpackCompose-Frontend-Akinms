package com.example.akinms.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.akinms.util.CART_TABLE

@Entity(tableName = CART_TABLE)
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idproducto: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    var cantidad: Int,
    val img: String,
    val idBodega: Int
)
