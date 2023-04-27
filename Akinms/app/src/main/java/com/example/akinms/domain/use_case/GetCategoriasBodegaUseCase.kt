package com.example.akinms.domain.use_case

import com.example.akinms.data.Result
import com.example.akinms.domain.model.Categoria
import com.example.akinms.domain.model.Products
import com.example.akinms.domain.repositories.CategoriaRepository
import com.example.akinms.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriasBodegaUseCase @Inject constructor(
    private val respository: CategoriaRepository
) {
    operator fun invoke(id: Long): Flow<Result<List<Categoria>>> {
        return respository.getCategoriasBodega(id)
    }
}