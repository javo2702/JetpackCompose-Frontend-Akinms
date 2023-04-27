package com.example.akinms.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.akinms.R
import com.example.akinms.ui.Maps.MapsScreen
import com.example.akinms.ui.bodega.BodegaScreen
import com.example.akinms.ui.components.FullScreenLoading
import com.example.akinms.ui.settings.SettingsScreen
import com.example.akinms.ui.theme.PrimaryColor
import com.example.akinms.util.BottomBarScreen
import com.example.akinms.util.navigationGraph.CoreScreen
import com.example.akinms.util.navigationGraph.Graph
import com.example.akinms.util.navigationGraph.coreNavGraph
import com.example.akinms.util.navigationGraph.profileNavGraph

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var state = homeViewModel.state
    var bodegasPremium = state.bodegas
    var idCliente = navController.getBackStackEntry(Graph.HOME+"/{idcliente}").arguments?.getLong("idcliente")
    Scaffold(
        modifier = Modifier.fillMaxWidth(1f),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abrir menú desplegable")
                }
                Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = null)
                IconButton(onClick = { navController.navigate(BottomBarScreen.Profile.route)}) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Más", tint = PrimaryColor)
                }
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth(1f), horizontalAlignment = Alignment.CenterHorizontally){
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =  Modifier.padding(vertical = 20.dp))
                {
                    Text(
                        text = "Bodegas destacadas",
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            item {
                if(state.isLoading){
                    FullScreenLoading()
                } else{
                    LazyRow(
                        modifier = Modifier
                            .padding(bottom = 15.dp)
                            .fillMaxWidth(.9f)
                    ){
                        for(bode in bodegasPremium){
                            item{
                                Column(
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .height(150.dp)
                                        .width(140.dp)
                                        .border(
                                            width = 1.dp,
                                            color = PrimaryColor,
                                            shape = RoundedCornerShape(5.dp)
                                        )
                                        .clickable {
                                            navController.navigate(Graph.BODEGA + "/cliente/" + idCliente + "/" + bode.id)
                                        }
                                ) {
                                    Box(modifier = Modifier
                                        .padding(bottom = 10.dp)
                                        .fillMaxWidth(1f)
                                        .fillMaxHeight(.5f)
                                        .clip(shape = RoundedCornerShape(5.dp))
                                        .background(PrimaryColor)
                                    ) {
                                        Image(
                                            modifier = Modifier.align(Alignment.Center),
                                            painter = painterResource(id = R.drawable.tienda2), contentDescription = ""
                                        )
                                        Image(
                                            modifier = Modifier
                                                .padding(top = 20.dp, start = 65.dp)
                                                .align(Alignment.Center),
                                            painter = painterResource(id = R.drawable.discount), contentDescription = ""
                                        )
                                    }
                                    Column(modifier = Modifier
                                        .padding(start = 10.dp)
                                        .fillMaxWidth(1f)) {
                                        Text(text = bode.nombre, fontSize = 15.sp)
                                        Text(text = bode.direccion, fontSize = 13.sp)
                                    }

                                }
                            }
                        }
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =  Modifier.padding(bottom = 20.dp))
                {
                    Text(
                        text = "Descubre bodegas a tu alrededor",
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(200.dp)) {
                        Text(text = "Seleccione Bodega",textAlign = TextAlign.Center)
                        Button(onClick = { navController.navigate(CoreScreen.Maps.route+"/"+idCliente) }) {
                            Text(text = "Abrir Mapa")
                        }
                    }
                    IconButton(modifier = Modifier.width(150.dp),onClick = { navController.navigate(CoreScreen.Maps.route+"/"+idCliente) }) {
                        Image(
                            modifier = Modifier.height(120.dp),
                            painter = painterResource(id = R.drawable.gps), contentDescription = "")
                    }

                }
            }
            item{
                Row(modifier = Modifier.padding(top = 20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.width(200.dp)) {
                        Text(text = "Lista de bodegas disponibles", textAlign = TextAlign.Center)
                        Button(onClick = { navController.navigate(CoreScreen.Bodegas.route) }) {
                            Text(text = "Ver lista")
                        }
                    }
                    IconButton(modifier = Modifier.width(150.dp),onClick = { navController.navigate(CoreScreen.Bodegas.route) }) {
                        Image(
                            modifier = Modifier.height(60.dp),
                            painter = painterResource(id = R.drawable.plan), contentDescription = "")
                    }

                }
            }
        }
    }
}
