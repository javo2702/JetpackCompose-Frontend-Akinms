package com.example.akinms.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.akinms.ui.main.MainScreen
import com.example.akinms.ui.theme.AkinmsTheme

@Composable
fun AkinmsApp(){
    AkinmsTheme {
        val navController = rememberNavController()
        MainScreen(navController = navController)
    }
}