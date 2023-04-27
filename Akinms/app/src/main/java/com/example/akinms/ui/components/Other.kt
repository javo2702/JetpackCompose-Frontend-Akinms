package com.example.akinms.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.akinms.R

@Composable
fun Other(
    navController: NavHostController,
    ruta: String
){
    Box(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 13.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .height(180.dp)
            .background(Color(0xFFEF476F))
            .clickable { navController.navigate(ruta) }
    ){
        Text(
            modifier = Modifier.padding(top = 10.dp, start = 20.dp).align(Alignment.TopStart),
            text = "Ofrecemos los mejores productos", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White
        )
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.abarrotes), contentDescription =""
        )
    }
    Box(
        modifier = Modifier
            .padding(start = 25.dp, end = 25.dp, top = 13.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .height(180.dp)
            .background(Color(0xFF27C06C))
    ){
        Text(
            modifier = Modifier.padding(top = 10.dp, start = 20.dp).align(Alignment.TopStart),
            text = "Les deseamos \nuna", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White
        )
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.feliznavidad), contentDescription =""
        )
    }
}