package com.example.akinms.viewsborrar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.akinms.components.*
import com.example.akinms.domain.model.Categoria
import com.example.akinms.domain.model.Products
import com.example.akinms.ui.components.CategoriesSelector
import com.example.akinms.ui.components.FullScreenLoading
import com.example.akinms.ui.components.SearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsView(
    navController: NavHostController,
    isLoading: Boolean = false,
    productos: List<Products> = emptyList(),
    bodegaNombre: String,
    idBodega: Int,
    categorias: List<Categoria>
){
    LazyColumn(

    ) {
        item {
            SearchBar(
                navController = navController,
                name = bodegaNombre,
                idBodega = idBodega
            )
        }
        item {
            CategoriesSelector(listCategory = categorias,navController=navController, idBodega =idBodega)
        }
        item {
            if(isLoading) FullScreenLoading()
            else{
                ProductList(
                    navController = navController,
                    list = productos,
                    idBodega = idBodega
                )
            }

        }
    }
    //if(isLoading) FullScreenLoading()
}