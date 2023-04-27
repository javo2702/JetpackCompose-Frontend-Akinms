package com.example.akinms.ui.profile

import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.data.source.remote.dto.pedido.Pedido
import com.example.akinms.data.source.remote.dto.pedido.PedidoX

data class ProfileState(
    var cliente: Cliente? = null,
    val isLoading: Boolean = false
)