package com.example.akinms.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.akinms.domain.model.Categoria
import com.example.akinms.ui.bodega.categories.CategoryItem
import com.example.akinms.util.navigationGraph.BodegaScreen

@Composable
fun SomeCategories(
    idBodega: Int,
    navController: NavHostController,
    category: List<Categoria>
){
    Column(

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 18.dp, end = 18.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categorias",
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(BodegaScreen.Categories.route)
                },
                text = "ver todas",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color(0xFF38A3A5)
            )
        }
        for(cat in category.chunked(3)){
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                cat.forEach{
                    CategoryItem(category = it,navController = navController, idBodega = idBodega)
                }
            }
        }
    }
}
