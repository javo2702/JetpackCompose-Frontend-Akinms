package com.example.akinms.util.navigationGraph
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable


import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        homeNavGraph(navController = navController)
    }

}
object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val CORE = "core_graph"
    const val PROFILE = "profile_graph"
    const val BODEGA = "bodega_graph"
    const val HISTORIAL = "historial_graph"
}
