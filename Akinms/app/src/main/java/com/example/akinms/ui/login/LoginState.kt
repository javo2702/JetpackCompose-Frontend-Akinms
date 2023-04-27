package com.example.akinms.ui.login

import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Products

data class LoginState(
    var cliente: Cliente? = null,
    var isLoading: Boolean = false
)