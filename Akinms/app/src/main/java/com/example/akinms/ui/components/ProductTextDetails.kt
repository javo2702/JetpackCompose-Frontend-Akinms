package com.example.akinms.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.akinms.domain.model.Products

@Composable
fun ProductTextDetails(producto: Products){
    Column(
        modifier = Modifier
            .padding(start = 8.dp, top = 4.dp)
            .height(75.dp)
    ) {
        Text(
            text = producto.nombre,
            fontSize = 14.sp,
            maxLines = 2
        )
        Text(
            text = producto.descripcion,
            color = Color(0xFFA1A1A1),
            fontSize = 13.sp,
        )
        Text(
            text = "S/. "+producto.precio.toString(),
            fontSize = 16.sp
        )
    }
}