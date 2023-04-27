package com.example.akinms.data.source.remote.dto.pedido

data class Pedido(
    var bodega: Bodega2,
    var cliente: Cliente,
    var detallesPedido: List<DetallesPedido>,
    var estado: String,
    var fecha: String,
    //var idpedido: Int? = 0,
    var metodo_pago: String,
    var monto_total: Double,
    var tipo_entrega: String
)