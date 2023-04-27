package com.example.akinms.util.navigationGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.akinms.ui.profile.historial.DetailsScreen
import com.example.akinms.ui.profile.historial.HistorialScreen
import com.example.akinms.ui.profile.historial.StatusScreen

fun NavGraphBuilder.historialNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HISTORIAL,
        startDestination = ProfileScreen.Historial.route+"/{id_cliente}"
    ) {
        composable(route = ProfileScreen.Historial.route+"/{id_cliente}",
        arguments = listOf(navArgument("id_cliente"){type = NavType.LongType},)
        ) {
            HistorialScreen(navController = navController)
        }
        composable(route = HistorialScreen.Status.route){
            StatusScreen(navController = navController)
        }
        composable(route = HistorialScreen.Details.route+"/cliente/{id_cliente}/pedido/{id_pedido}",
            arguments = listOf(
                navArgument("id_cliente"){type = NavType.LongType},
                navArgument("id_pedido"){type = NavType.LongType}
            )
        ){
            DetailsScreen(navController = navController)
        }

    }
}

sealed class HistorialScreen(val route: String) {
    object Status: HistorialScreen(route = "STATUS")
    object Details: HistorialScreen(route = "DETAILS")
}