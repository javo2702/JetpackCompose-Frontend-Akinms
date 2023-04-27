package com.example.akinms.domain.use_case

import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.pedido.Pedido
import com.example.akinms.data.source.remote.dto.pedido.PedidoX
import com.example.akinms.domain.repositories.PedidoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPedidosClienteUseCase @Inject constructor(
    private val repository: PedidoRepository
) {
    operator fun invoke(id:Long): Flow<Result<List<PedidoX>>>{
        return repository.getPedidosCliente(id)
    }
}