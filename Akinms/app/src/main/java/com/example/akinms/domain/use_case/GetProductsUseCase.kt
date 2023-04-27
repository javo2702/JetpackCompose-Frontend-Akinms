package com.example.akinms.domain.use_case

import com.example.akinms.domain.model.Product
import com.example.akinms.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.akinms.data.Result
import com.example.akinms.domain.model.Products

class GetProductsUseCase @Inject constructor(
    private val respository: ProductRepository
) {
    operator fun invoke(id: Long): Flow<Result<List<Products>>>{
        return respository.getProducts(id)
    }
}