package com.example.akinms.data.source.remote.dto.pedido

data class PedidoDto2(
    val mensaje: String,
    val pedidos: List<PedidoX>
)
fun PedidoDto2.toPedidos(): List<PedidoX>{
    return pedidos
}