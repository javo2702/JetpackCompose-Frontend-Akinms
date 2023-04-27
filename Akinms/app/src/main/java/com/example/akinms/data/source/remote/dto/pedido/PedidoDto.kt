package com.example.akinms.data.source.remote.dto.pedido

data class PedidoDto(
    val mensaje: String,
    val pedidos: List<Pedido>
)
fun PedidoDto.toPedidos(): List<Pedido>{
    return pedidos
}