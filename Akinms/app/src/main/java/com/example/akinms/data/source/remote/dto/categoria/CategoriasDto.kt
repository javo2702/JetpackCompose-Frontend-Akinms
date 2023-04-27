package com.example.akinms.data.source.remote.dto.categoria

import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.Categoria
import com.google.android.gms.maps.model.LatLng

data class CategoriasDto(
    val categorias: List<CategoriaDto>,
    val mensaje: String
)
fun CategoriasDto.toCategories(): List<Categoria>{
    val resultEntries = categorias.mapIndexed{_,entries ->
        Categoria(
            idcategoria = entries.idcategoria,
            nombre = entries.nombre,
            img = entries.img_url
        )
    }
    return resultEntries
}