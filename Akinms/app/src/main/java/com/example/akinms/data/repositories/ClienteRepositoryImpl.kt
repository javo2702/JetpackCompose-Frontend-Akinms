package com.example.akinms.data.repositories

import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.ApiRest
import com.example.akinms.data.source.remote.dto.bodega.toBodega
import com.example.akinms.data.source.remote.dto.bodega.toListBodegas
import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.data.source.remote.dto.cliente.toCliente
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.repositories.BodegaRepository
import com.example.akinms.domain.repositories.ClienteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ClienteRepositoryImpl @Inject constructor(
    private val api: ApiRest
) : ClienteRepository {
    override suspend fun getCliente(id: Long): Result<Cliente> {
        val response = try{
            api.getCliente(id)
        } catch (e:java.lang.Exception){
            return Result.Error("An unknown error occurred")
        }
        return Result.Success(response.toCliente())
    }

    override suspend fun buscarCliente(cliente: Cliente): Result<Cliente> {
        println(cliente.correo+" "+cliente.contraseña)
        val response = try{
            api.buscarCliente(cliente.correo,cliente.contraseña)
        } catch (e:java.lang.Exception){
            println("ERROR AQUI")
            println(e.message)
            return Result.Error("An unknown error occurred")
        }
        return Result.Success(response.toCliente())
    }
}