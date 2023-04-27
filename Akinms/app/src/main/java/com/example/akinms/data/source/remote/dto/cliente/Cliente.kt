package com.example.akinms.data.source.remote.dto.cliente

data class Cliente(
    val nombres: String,
    val apellidos: String,
    val direccion: String,
    val telefono: String,
    val correo: String,
    val contraseña: String,
    val idcliente: Int,
)