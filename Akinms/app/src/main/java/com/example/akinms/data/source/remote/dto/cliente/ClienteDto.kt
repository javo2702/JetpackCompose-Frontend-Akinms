package com.example.akinms.data.source.remote.dto.cliente

data class ClienteDto(
    val mensaje: String,
    val cliente: Cliente
)
fun ClienteDto.toCliente(): Cliente{
    return cliente
}