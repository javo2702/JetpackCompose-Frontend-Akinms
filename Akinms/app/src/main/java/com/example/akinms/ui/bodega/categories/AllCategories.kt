package com.example.akinms.ui.bodega.categories

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.akinms.domain.model.Categoria

@Composable
fun AllCategories(
    navController: NavHostController,
    category: List<Categoria>,
    idBodega: Int,
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 18.dp, end = 18.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Todas las Categorias",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        for(cat in category.chunked(3)){
            Row(
                modifier = Modifier.padding(8.dp),
            ) {
                cat.forEach{
                    CategoryItem(category = it, navController = navController, idBodega = idBodega)
                }
            }
        }
    }
}