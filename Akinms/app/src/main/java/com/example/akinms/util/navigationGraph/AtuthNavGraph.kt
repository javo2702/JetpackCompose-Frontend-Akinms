package com.example.akinms.util.navigationGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.akinms.ui.login.LoginView

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginView(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                },
                navController = navController
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
}