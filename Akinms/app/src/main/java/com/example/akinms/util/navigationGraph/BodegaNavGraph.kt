package com.example.akinms.util.navigationGraph

import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.akinms.ui.bodega.BodegaScreen
import com.example.akinms.ui.bodega.categories.CategoriesScreen
import com.example.akinms.ui.bodega.cart.CartScreen
import com.example.akinms.ui.bodega.products.ProductsScreen

fun NavGraphBuilder.bodegaNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.BODEGA,
        startDestination = BodegaScreen.Bodega.route+"/{id}"
    ) {
        composable(route = BodegaScreen.Bodega.route+"/{id}", arguments = listOf(navArgument("id"){type= NavType.LongType})){backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            requireNotNull(id)
            //BodegaScreen(navController)
        }
        composable(route = BodegaScreen.Categories.route){
            //AllCategoriesView(navController = navController)
            //CategoriesScreen(navController = navController)
        }
        composable(
            route = BodegaScreen.Products.route+"/{id}",
            arguments = listOf(navArgument("id"){type= NavType.LongType})
        ) {
            //ProductsScreen(navController = navController)
        }
        composable(route = BodegaScreen.Cart.route) {
            //CartScreen(navController = navController)
        }
    }
}
sealed class BodegaScreen(val route: String) {
    object Bodega: BodegaScreen(route = "BODEGAMAIN")
    object Categories: BodegaScreen(route = "CATEGORIES")
    object Products : BodegaScreen(route = "PRODUCTS")
    object Cart: BodegaScreen(route = "CART")
    object CheckOut: BodegaScreen(route = "CHECKOUT")
}