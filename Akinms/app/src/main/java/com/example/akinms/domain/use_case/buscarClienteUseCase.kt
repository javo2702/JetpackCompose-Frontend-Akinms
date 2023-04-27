package com.example.akinms.domain.use_case

import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.domain.repositories.ClienteRepository

class buscarClienteUseCase @Inject constructor(
    private val respository: ClienteRepository
) {
    suspend operator fun invoke(cliente: Cliente): Result<Cliente>{
        return respository.buscarCliente(cliente)
    }
}