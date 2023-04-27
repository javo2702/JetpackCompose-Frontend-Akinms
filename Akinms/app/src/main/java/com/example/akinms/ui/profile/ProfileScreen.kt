package com.example.akinms.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.akinms.R
import com.example.akinms.ui.theme.PrimaryColor
import com.example.akinms.util.BottomBarScreen
import com.example.akinms.util.navigationGraph.Graph
import com.example.akinms.util.navigationGraph.ProfileScreen

@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedGetBackStackEntry")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
){
    var state = profileViewModel.state
    var id = navController.getBackStackEntry(Graph.HOME+"/{idcliente}").arguments?.getLong("idcliente")
    if (id != null) {
        profileViewModel.getUsuario(id)
    }
    Column(
        modifier = Modifier
            .fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(PrimaryColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "", tint = Color.White)
            }
            Text(text = "Vista de Usuario", color = Color.White,fontWeight = FontWeight.Bold,)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            if(state.cliente!=null){
                item{
                    Column(modifier = Modifier
                        .padding(top = 20.dp, bottom = 10.dp)
                        .fillMaxWidth(.9f)
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp),
                            text = "Datos Personales", fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                        )
                        Text(text = "Nombres:")
                        Box(modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(1f)
                            .border(
                                width = 1.dp,
                                color = PrimaryColor,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(10.dp)) {
                            Text(text = state.cliente!!.nombres)
                        }
                        Text(text = "Apellidos:")
                        Box(modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(1f)
                            .border(
                                width = 1.dp,
                                color = PrimaryColor,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(10.dp)) {
                            Text(text = state.cliente!!.apellidos)
                        }
                        Text(text = "Telefono:")
                        Box(modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(1f)
                            .border(
                                width = 1.dp,
                                color = PrimaryColor,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(10.dp)) {
                            Text(text = state.cliente!!.telefono)
                        }
                        Text(text = "Direccion:")
                        Box(modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth(1f)
                            .border(
                                width = 1.dp,
                                color = PrimaryColor,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(10.dp)) {
                            Text(text = state.cliente!!.direccion)
                        }
                    }
                }
            }

            item{
                Column(modifier = Modifier

                    .fillMaxWidth(.9f)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
                        text = "Historial de Compras",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(.78f),
                            onClick = { navController.navigate(ProfileScreen.Historial.route+"/"+state.cliente?.idcliente) }) {
                            Text(text = "Ver historial")
                        }
                        IconButton(onClick = { navController.navigate(ProfileScreen.Historial.route) }) {
                            Image(
                                painter = painterResource(id = R.drawable.clock),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun BottomBar1(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}