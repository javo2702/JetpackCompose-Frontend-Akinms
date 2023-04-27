package com.example.akinms.ui.bodega.cart.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CartTotalResume(
    total:Double
){
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(80.dp)
            .border(width = 1.dp, color = Color(0xFFFFC532), shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "SubTotal:"
            )
            Text(
                text = "IGV:"
            )
            Text(
                text = "Total"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "S/. "+total.toString()
            )
            Text(
                text = "S/. 0.00"
            )
            Text(
                text = "S/. "+total.toString(),
                fontWeight = FontWeight.Bold
            )
        }
    }
}