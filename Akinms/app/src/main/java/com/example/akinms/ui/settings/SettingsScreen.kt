package com.example.akinms.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.akinms.R
import com.example.akinms.ui.theme.PrimaryColor

@Composable
fun SettingsScreen(
    navController: NavHostController,
){
    Column(
        modifier = Modifier
            .fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(1f).background(PrimaryColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "", tint = Color.White)
            }
            Text(text = "Configuración", color = Color.White,fontWeight = FontWeight.Bold,)
        }
    }
}