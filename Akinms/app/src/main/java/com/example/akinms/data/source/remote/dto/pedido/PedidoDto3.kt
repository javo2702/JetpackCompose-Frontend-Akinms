package com.example.akinms.data.source.remote.dto.pedido

data class PedidoDto3(
    val mensaje: String,
    val pedidos: PedidoX
)
fun PedidoDto3.toPedidos(): PedidoX{
    return pedidos
}