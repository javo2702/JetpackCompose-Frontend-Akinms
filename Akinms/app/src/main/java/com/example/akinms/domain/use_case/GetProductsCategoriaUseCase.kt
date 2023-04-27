package com.example.akinms.domain.use_case

import com.example.akinms.domain.model.Product
import com.example.akinms.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.domain.model.Products

class GetProductsCategoriaUseCase @Inject constructor(
    private val respository: ProductRepository
) {
    operator fun invoke(id1: Long, id2: Long): Flow<Result<List<Products>>>{
        return respository.getProductsCategoria(id1,id2)
    }
}