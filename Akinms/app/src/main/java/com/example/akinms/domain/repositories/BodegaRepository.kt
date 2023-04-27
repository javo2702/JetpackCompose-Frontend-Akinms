package com.example.akinms.domain.repositories

import com.example.akinms.data.Result
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Product
import com.example.akinms.domain.model.Products
import kotlinx.coroutines.flow.Flow

interface BodegaRepository {

    fun getBodegas() : Flow<Result<List<Bodega>>>

    fun getBodegasPremium() : Flow<Result<List<Bodega>>>

    suspend fun getBodega(id:Long): Result<Bodega>

}