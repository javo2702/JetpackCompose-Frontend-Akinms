package com.example.akinms.ui.profile.historial

import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.data.source.remote.dto.pedido.Pedido
import com.example.akinms.data.source.remote.dto.pedido.PedidoX

data class HistorialState(
    val pedidos: List<PedidoX> = emptyList(),
    val isLoading: Boolean = false
)