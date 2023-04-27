package com.example.akinms.data.repositories

import com.example.akinms.data.Result
import com.example.akinms.data.source.remote.ApiRest
import com.example.akinms.data.source.remote.dto.bodega.toBodega
import com.example.akinms.data.source.remote.dto.bodega.toListBodegas
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.repositories.BodegaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BodegaRepositoryImpl @Inject constructor(
    private val api: ApiRest
) : BodegaRepository {
    override fun getBodegas(): Flow<Result<List<Bodega>>> = flow() {
        emit(Result.Loading())
        try{
            val response = api.getBodegas().toListBodegas()
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

    override fun getBodegasPremium(): Flow<Result<List<Bodega>>> = flow {
        emit(Result.Loading())
        try{
            val response = api.getBodegasPremium().toListBodegas()
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

    override suspend fun getBodega(id:Long): Result<Bodega> {
        val response = try{
            api.getBodega(id)
        } catch (e:java.lang.Exception){
            return Result.Error("An unknown error occurred")
        }
        return Result.Success(response.toBodega())
    }
}