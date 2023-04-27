package com.example.akinms.ui.Maps.bodegas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.akinms.R
import com.example.akinms.ui.Maps.MapContainer
import com.example.akinms.ui.Maps.MapsViewModel
import com.example.akinms.ui.components.FullScreenLoading
import com.example.akinms.ui.theme.PrimaryColor
import com.example.akinms.util.navigationGraph.CoreScreen
import com.example.akinms.util.navigationGraph.Graph
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun TodasBodegasScreen(
    navController: NavHostController,
    mapsViewModel: MapsViewModel = hiltViewModel()
){
    var idCliente = navController.getBackStackEntry(Graph.HOME+"/{idcliente}").arguments?.getLong("idcliente")
    println("ID DEL CLIENTE: "+idCliente)
    val state = mapsViewModel.state
    val eventFlow = mapsViewModel.eventFlow
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        eventFlow.collectLatest { event ->
            when(event) {
                is MapsViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
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
            Text(text = "Encuentra tu bodega cercana", color = Color.White,fontWeight = FontWeight.Bold,)
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(15.dp)
        ){
            item {
                Row(modifier = Modifier
                    .padding(bottom = 15.dp)
                    .fillMaxWidth(.95f), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Bodegas", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Text(
                        modifier = Modifier.clickable { navController.navigate(CoreScreen.Maps.route+"/"+idCliente) },
                        text = "Ver en mapa", color = Color.Blue, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            if(state.isLoading){
                item { FullScreenLoading() }
            } else{
                for(bodega in state.bodegas){
                    item {
                        Column(modifier = Modifier
                            .fillMaxWidth(1f)
                            .clickable { navController.navigate(Graph.BODEGA + "/cliente/" + idCliente + "/" + bodega.id) }
                        ) {
                            Row() {
                                IconButton(onClick = { /*navController.navigate(Graph.BODEGA+"/"+bodega.id)*/}) {
                                    //Icon(painter = painterResource(id = R.drawable.tienda), contentDescription = "")
                                    Image(painter = painterResource(id = R.drawable.tienda2), contentDescription = "")
                                }
                                Column(modifier = Modifier.padding(start = 20.dp)) {
                                    Text(text = bodega.nombre, fontSize = 19.sp, fontWeight = FontWeight.SemiBold)
                                    Text(text = bodega.direccion)
                                }
                            }
                        }
                        Divider(
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .fillMaxWidth(1f),
                            thickness = 1.dp, color = PrimaryColor
                        )
                    }
                }
            }

        }
    }
}