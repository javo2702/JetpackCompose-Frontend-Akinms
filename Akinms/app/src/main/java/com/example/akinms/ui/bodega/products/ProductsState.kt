package com.example.akinms.ui.bodega.products


import com.example.akinms.domain.model.Products

data class ProductsState(
    val productos: List<Products> = emptyList(),
    var isLoading: Boolean = false
)