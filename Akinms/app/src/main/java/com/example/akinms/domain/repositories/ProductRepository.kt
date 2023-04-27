package com.example.akinms.domain.repositories

import com.example.akinms.data.Result
import com.example.akinms.domain.model.Product
import com.example.akinms.domain.model.Products
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(id: Long) : Flow<Result<List<Products>>>
    fun getProductsNombre(id: Long,valor: String) : Flow<Result<List<Products>>>
    fun getProductsCategoria(id1:Long, id2:Long) : Flow<Result<List<Products>>>
}