package com.example.akinms.ui.bodega

import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Categoria

data class BodegaState(
    val bodega: Bodega? = null,
    val categorias: List<Categoria> = emptyList(),
    val isLoading: Boolean = false
)
