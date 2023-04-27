package com.example.akinms.domain.repositories

import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Product
import com.example.akinms.domain.model.Products
import kotlinx.coroutines.flow.Flow

interface ClienteRepository {

    suspend fun getCliente(id:Long): Result<Cliente>
    suspend fun buscarCliente(cliente:Cliente): Result<Cliente>
}