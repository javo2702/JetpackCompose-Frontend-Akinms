package com.example.akinms.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "Inicio",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "Perfil",
        icon = Icons.Default.Person
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = "Configuraciones",
        icon = Icons.Default.Settings
    )
}
