package com.example.akinms.domain.use_case


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Products
import com.example.akinms.domain.repositories.BodegaRepository

class GetBodegaUseCase @Inject constructor(
    private val respository: BodegaRepository
) {
    suspend operator fun invoke(id:Long): Result<Bodega>{
        return respository.getBodega(id)
    }
}