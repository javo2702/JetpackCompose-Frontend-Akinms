package com.example.akinms.util.navigationGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.akinms.ui.profile.ProfileScreen
import com.example.akinms.util.BottomBarScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = BottomBarScreen.Profile.route
    ) {
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(navController = navController)
        }
        historialNavGraph(navController)
    }
}

sealed class ProfileScreen(val route: String) {
    object Historial: ProfileScreen(route = "HISTORIAL")
}