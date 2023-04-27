package com.example.akinms.data.source.remote.dto.pedido

data class PedidoDto4(
    val mensaje: String,
    val pedidos: PedidoX
)
fun PedidoDto4.toPedidos(): PedidoX{
    return pedidos
}