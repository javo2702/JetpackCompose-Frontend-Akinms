package com.example.akinms.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.akinms.domain.model.Categoria
import com.example.akinms.util.navigationGraph.BodegaScreen

@Composable
fun CategoriesSelector(
    listCategory: List<Categoria>,
    idBodega: Int,
    navController: NavHostController
){
    LazyRow(
        modifier = Modifier.padding(top = 10.dp, start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        content = {
            for (it in listCategory){
                item {
                    Box(
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                            .height(28.dp)
                            .border(width = 1.dp, color = Color(0xFFE5E5E5),shape= RoundedCornerShape(10.dp))
                            .clickable {
                                navController.popBackStack()
                                navController.navigate(route = BodegaScreen.Products.route+"/bodega/"+idBodega+"/categoria/"+it.idcategoria)
                            }
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center).padding(horizontal = 8.dp, vertical = 4.dp),
                            text = it.nombre,
                            color = Color(0xFF274C77)
                        )
                    }
                }
            }
        }
    )
}