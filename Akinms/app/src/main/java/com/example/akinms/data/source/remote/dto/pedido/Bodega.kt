package com.example.akinms.data.source.remote.dto.pedido

data class Bodega(
    var idbodega: Int,
    val direccion: String? = "",
    val latitud: String? = "",
    val longitud: String? = "",
    val nombre: String? = ""
)