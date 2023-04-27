package com.example.akinms.data.repositories

import com.example.akinms.data.source.remote.ApiRest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.dto.bodega.toBodega
import com.example.akinms.data.source.remote.dto.pedido.Pedido
import com.example.akinms.data.source.remote.dto.pedido.PedidoX
import com.example.akinms.data.source.remote.dto.pedido.toPedidos
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.repositories.PedidoRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PedidoRepositoryImpl @Inject constructor(
    private val api: ApiRest
) : PedidoRepository {
    override fun getPedidosCliente(id: Long): Flow<Result<List<PedidoX>>>  = flow{
        emit(Result.Loading())
        try{
            val response = api.getPedidoByClient(id).toPedidos()
            emit(Result.Success(response))
        } catch (e: HttpException){
            emit(Result.Error(
                message = "Oops, something went wrong",
                data = null
            ))
        } catch (e: IOException){
            emit(Result.Error(
                message = "Couldn't reach server, check your internet connection",
                data = null
            ))
        }
    }

    override suspend fun getDetallePedidoCliente(id_cliente: Long, id_pedido: Long): Result<PedidoX>{
        val response = try{
            api.getDetallePedidoCliente(id_cliente,id_pedido)
        } catch (e:java.lang.Exception){
            return Result.Error("An unknown error occurred")
        }
        println("LLEGO ACÁ GAAAAAAAAAAAAAAAAAAAAAAA")
        return Result.Success(response.toPedidos())
    }

    override suspend fun setPedido(pedido: Pedido): Result<PedidoX> {
        val response = try{
            api.setPedido(pedido)
        } catch (e:java.lang.Exception){
            return Result.Error("An unknown error occurred")
        }
        return Result.Success(response.toPedidos())
    }
}