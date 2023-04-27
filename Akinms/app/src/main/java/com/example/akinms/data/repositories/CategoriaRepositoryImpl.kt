package com.example.akinms.data.repositories

import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.ApiRest
import com.example.akinms.data.source.remote.dto.bodega.toBodega
import com.example.akinms.data.source.remote.dto.bodega.toListBodegas
import com.example.akinms.data.source.remote.dto.categoria.toCategories
import com.example.akinms.data.source.remote.dto.producto.toListProductos
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Categoria
import com.example.akinms.domain.repositories.BodegaRepository
import com.example.akinms.domain.repositories.CategoriaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoriaRepositoryImpl @Inject constructor(
    private val api: ApiRest
) : CategoriaRepository{
    override fun getCategoriasBodega(id: Long): Flow<Result<List<Categoria>>> = flow {
        emit(Result.Loading())
        try{
            val response = api.getAllCategoriesBodega(id).toCategories()
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