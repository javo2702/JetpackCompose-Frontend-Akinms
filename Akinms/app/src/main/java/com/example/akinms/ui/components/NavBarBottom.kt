package com.example.akinms.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.akinms.R

@Composable
fun NavBarBottom() {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .height(60.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Column(
            modifier = Modifier
                .padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 4.dp)
                .width(80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(25.dp),
                painter = painterResource(id = R.drawable.home), contentDescription = "home",
            )
            Text(
                text = "Inicio",
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 4.dp)
                .width(80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(25.dp),
                painter = painterResource(id = R.drawable.account), contentDescription = "home"
            )
            Text(
                text = "Perfil",
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 4.dp)
                .width(80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(25.dp),
                painter = painterResource(id = R.drawable.cog), contentDescription = "home"
            )
            Text(
                text = "Configuracion",
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }

}