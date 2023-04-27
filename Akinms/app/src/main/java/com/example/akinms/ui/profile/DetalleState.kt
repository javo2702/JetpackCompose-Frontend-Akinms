package com.example.akinms.ui.profile

import com.example.akinms.data.source.remote.dto.pedido.Pedido
import com.example.akinms.data.source.remote.dto.pedido.PedidoX

data class DetalleState(
    val pedidos: PedidoX? = null,
    val isLoading: Boolean = false
)