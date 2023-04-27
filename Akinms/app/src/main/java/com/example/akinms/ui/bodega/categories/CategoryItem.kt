package com.example.akinms.ui.bodega.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.akinms.domain.model.Categoria
import com.example.akinms.util.navigationGraph.BodegaScreen


val colors = listOf<Long>(0xFF98F5E1,0xFF90DBF4,0xFFFBF8CC,0xFFFDE4CF,0xFFF1C0E8,0xFFB9FBC0,0xFFCBF3F0,0xFFFAE1DD)


@Composable
fun CategoryItem(
    idBodega: Int,
    category: Categoria,
    navController: NavHostController){
    Column(
        modifier = Modifier.width(120.dp).padding(horizontal = 12.dp, vertical = 8.dp).clickable {
            navController.navigate(route = BodegaScreen.Products.route+"/bodega/"+idBodega+"/categoria/"+category.idcategoria)
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(colors.random()))
                .height(100.dp)
                .width(100.dp)
        ){
            Image(
                modifier = Modifier.align(Alignment.Center).height(70.dp).width(70.dp),
                painter = rememberAsyncImagePainter(category.img),
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = category.nombre,
            textAlign = TextAlign.Center
        )
    }
}
