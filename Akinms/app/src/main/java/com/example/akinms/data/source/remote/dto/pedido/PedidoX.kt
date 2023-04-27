package com.example.akinms.data.source.remote.dto.pedido

data class PedidoX(
    val bodega: Bodega,
    val detallesPedido: List<DetallesPedidoX>,
    val estado: String,
    val fecha: String,
    val idpedido: Int,
    val metodo_pago: String,
    val monto_total: Double,
    val tipo_entrega: String
)