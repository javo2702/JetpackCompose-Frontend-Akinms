package com.example.akinms.domain.use_case


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.pedido.Pedido
import com.example.akinms.data.source.remote.dto.pedido.PedidoX
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Products
import com.example.akinms.domain.repositories.BodegaRepository
import com.example.akinms.domain.repositories.PedidoRepository

class SetPedidoUseCase @Inject constructor(
    private val respository: PedidoRepository
) {
    suspend operator fun invoke(pedido: Pedido): Result<PedidoX>{
        return respository.setPedido(pedido)
    }
}