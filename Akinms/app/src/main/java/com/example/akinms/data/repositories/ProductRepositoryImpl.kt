package com.example.akinms.data.repositories

import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.ApiRest
import com.example.akinms.data.source.remote.dto.producto.toListProductos
import com.example.akinms.domain.model.Products
import com.example.akinms.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ApiRest
) : ProductRepository{
    override fun getProducts(id: Long): Flow<Result<List<Products>>> = flow() {
        emit(Result.Loading())
        try{
            val response = api.getAllProducts(id).toListProductos()
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

    override fun getProductsNombre(id: Long, valor: String): Flow<Result<List<Products>>> = flow{
        emit(Result.Loading())
        try{
            val response = api.getProductsByName(id,valor).toListProductos()
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

    override fun getProductsCategoria(id1: Long, id2: Long): Flow<Result<List<Products>>> = flow() {
        emit(Result.Loading())
        try{
            val response = api.getProductsCategoria(id1,id2).toListProductos()
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
}