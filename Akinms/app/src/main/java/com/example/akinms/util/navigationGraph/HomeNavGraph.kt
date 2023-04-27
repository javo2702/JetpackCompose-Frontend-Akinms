package com.example.akinms.util.navigationGraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.akinms.ui.settings.SettingsScreen
import com.example.akinms.util.BottomBarScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
){
    navigation(
        route = Graph.HOME+"/{idcliente}",
        startDestination = Graph.CORE,
        arguments = listOf(navArgument("idcliente"){type= NavType.LongType})
    ) {
        coreNavGraph(navController=navController)
        profileNavGraph(navController=navController)
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(navController=navController)
        }
    }
}