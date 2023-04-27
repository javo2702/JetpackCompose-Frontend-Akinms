package com.example.akinms.domain.model

import com.google.android.gms.maps.model.LatLng

data class Bodega(
    val id: Int,
    val nombre: String,
    val ubicacion: LatLng,
    val direccion: String
    )