package com.example.akinms.ui.bodega.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.akinms.ui.components.SearchBar
import com.example.akinms.domain.model.Categoria

@Composable
fun CategoriesScreen(
    navController: NavHostController,
    bodegaNombre: String,
    listaCategorias: List<Categoria>,
    idBodega: Int,
){
    Column() {
        SearchBar(
            navController = navController,
            name = bodegaNombre,
            idBodega = idBodega
        )
        AllCategories(navController, category = listaCategorias, idBodega = idBodega)
    }
}