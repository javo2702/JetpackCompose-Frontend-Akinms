package com.example.akinms.domain.repositories

import com.example.akinms.data.source.remote.dto.pedido.Pedido
import kotlinx.coroutines.flow.Flow
import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.pedido.PedidoX

interface PedidoRepository {
    fun getPedidosCliente(id: Long) : Flow<Result<List<PedidoX>>>
    suspend fun getDetallePedidoCliente(id_cliente: Long, id_pedido: Long): Result<PedidoX>
    suspend fun setPedido(pedido: Pedido) : com.example.akinms.data.Result<PedidoX>
}

