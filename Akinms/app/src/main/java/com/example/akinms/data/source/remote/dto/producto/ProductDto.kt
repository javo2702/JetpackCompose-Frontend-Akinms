package com.example.akinms.data.source.remote.dto.producto

import com.example.akinms.domain.model.Products


data class ProductDto(
    val mensaje: String,
    val productos: List<Producto>
)

fun ProductDto.toListProductos(): List<Products>{
    val resultEntries = productos.mapIndexed{_,entries ->
        Products(
            id = entries.idProducto,
            nombre = entries.nombre,
            descripcion = entries.descripcion,
            precio = entries.precio,
            descuento = entries.descuento,
            stock = entries.stock,
            img = entries.img
        )
    }
    return resultEntries
}