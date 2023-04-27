package com.example.akinms.domain.use_case


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Products
import com.example.akinms.domain.repositories.BodegaRepository
import com.example.akinms.domain.repositories.ClienteRepository

class GetClienteUseCase @Inject constructor(
    private val respository: ClienteRepository
) {
    suspend operator fun invoke(id:Long): Result<Cliente>{
        return respository.getCliente(id)
    }
}