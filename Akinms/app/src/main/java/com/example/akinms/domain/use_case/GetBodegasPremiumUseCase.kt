package com.example.akinms.domain.use_case


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Products
import com.example.akinms.domain.repositories.BodegaRepository

class GetBodegasPremiumUseCase @Inject constructor(
    private val respository: BodegaRepository
) {
    operator fun invoke(): Flow<Result<List<Bodega>>>{
        return respository.getBodegasPremium()
    }
}