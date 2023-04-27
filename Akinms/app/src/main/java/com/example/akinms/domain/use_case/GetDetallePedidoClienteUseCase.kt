package com.example.akinms.domain.use_case

import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.pedido.PedidoX
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.repositories.BodegaRepository
import com.example.akinms.domain.repositories.PedidoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetallePedidoClienteUseCase @Inject constructor(
    private val repository: PedidoRepository
) {
    suspend operator fun invoke(id_cliente:Long,id_pedido:Long): Result<PedidoX>{
        return repository.getDetallePedidoCliente(id_cliente,id_pedido)
    }
}