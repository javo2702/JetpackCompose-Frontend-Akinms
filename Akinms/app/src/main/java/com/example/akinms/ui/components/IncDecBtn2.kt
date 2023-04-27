package com.example.akinms.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.akinms.domain.model.CartItem
import com.example.akinms.ui.bodega.cart.CartViewModel

@Composable
fun IncDecBtn2(
    cantidad: MutableState<Int>,
    context: Context
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cantidad:")
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .border(width = 1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(10.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .background(Color(0xFFD9D9D9))
                    .clickable {
                        if (cantidad.value > 1) {
                            cantidad.value--
                        } else {
                            Toast
                                .makeText(
                                    context,
                                    "Cantidad minima es 1",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    }
            ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "-"
                )
            }
            Box(
                modifier = Modifier
                    .width(38.dp)
                    .height(30.dp)
            ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = cantidad.value.toString()
                )
            }
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .background(Color(0xFFD9D9D9))
                    .clickable {
                        cantidad.value++
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "+"
                )
            }
        }
    }
}