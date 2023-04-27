package com.example.akinms.data.source.remote.dto.bodega

import com.example.akinms.domain.model.Bodega
import com.google.android.gms.maps.model.LatLng

data class BodegaDto(
    val bodegas: Bodegas,
    val mensaje: String
)
fun BodegaDto.toBodega(): Bodega{
    return Bodega(
        id = bodegas.idbodega,
        nombre = bodegas.nombre,
        ubicacion = LatLng(bodegas.latitud.toDouble(),bodegas.longitud.toDouble()),
        direccion = bodegas.direccion
    )
}