package com.example.akinms.util.navigationGraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.akinms.ui.Maps.MapsScreen
import com.example.akinms.ui.Maps.bodegas.TodasBodegasScreen
import com.example.akinms.ui.bodega.BodegaScreen
import com.example.akinms.ui.home.HomeScreen
import com.example.akinms.ui.home.HomeViewModel
import com.example.akinms.util.BottomBarScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.coreNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.CORE,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(
            route = BottomBarScreen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(route = CoreScreen.Ofertas.route){
            Column() {
                Text(text = "All oferts Screen")
            }
        }
        composable(route = CoreScreen.Maps.route+"/{id_cli}",
            arguments = listOf(
                navArgument("id_cli"){type= NavType.LongType}
        )) { backStackEntry ->
            var id = backStackEntry.arguments?.getLong("id_cli")
            requireNotNull(id)
            MapsScreen(navController = navController,idCliente = id)
        }
        composable(route = Graph.BODEGA+"/cliente/{id_cli}/{id}",
            arguments = listOf(
                navArgument("id"){type= NavType.LongType},
                navArgument("id_cli"){type= NavType.LongType}
            )) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id")
            requireNotNull(id)
            val id2 = backStackEntry.arguments?.getLong("id_cli")
            requireNotNull(id)
            requireNotNull(id2)
            BodegaScreen(coreNavController = navController, id = id, id_cli = id2)
        }
        composable(route = CoreScreen.Bodegas.route){
            TodasBodegasScreen(navController=navController)
        }
        //bodegaNavGraph(navController=navController)
    }
}
sealed class CoreScreen(val route: String) {
    object Ofertas: CoreScreen(route = "OFERTAS")
    object Maps : CoreScreen(route = "MAPS")
    object Bodegas: CoreScreen(route = "BODEGAS")
}