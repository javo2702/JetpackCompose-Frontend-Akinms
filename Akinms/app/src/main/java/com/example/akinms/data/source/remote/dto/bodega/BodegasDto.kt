package com.example.akinms.data.source.remote.dto.bodega

import com.example.akinms.domain.model.Bodega
import com.google.android.gms.maps.model.LatLng

data class BodegasDto(
    val bodegas: List<Bodegas>,
    val mensaje: String
)

fun BodegasDto.toListBodegas(): List<Bodega>{
    val resultEntries = bodegas.mapIndexed{_,entries ->
        Bodega(
            id = entries.idbodega,
            nombre = entries.nombre,
            ubicacion = LatLng(entries.latitud.toDouble(),entries.longitud.toDouble()),
            direccion = entries.direccion
        )
    }
    return resultEntries
}