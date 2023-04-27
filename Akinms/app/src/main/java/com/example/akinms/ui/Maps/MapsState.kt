package com.example.akinms.ui.Maps

import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Products

data class MapsState(
    val bodegas: List<Bodega> = emptyList(),
    val isLoading: Boolean = false
)