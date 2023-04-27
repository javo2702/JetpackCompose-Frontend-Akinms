package com.example.akinms.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.akinms.domain.model.Products


@Composable
fun ProductList(
    navController: NavHostController,
    list: List<Products>,
    idBodega: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(list.size>0){
            for(prod in list.chunked(2)){
                Row(
                    modifier = Modifier.height(245.dp)
                ) {
                    prod.forEach{
                        ProductItem(
                            producto = it,
                            navController = navController,
                            idBodega = idBodega
                        )
                    }
                }
            }
        }
        else{
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "No se encontraron resultados"
            )
        }

    }
}
