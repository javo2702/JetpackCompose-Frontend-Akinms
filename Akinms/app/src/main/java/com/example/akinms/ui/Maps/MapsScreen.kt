package com.example.akinms.ui.Maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.akinms.R
import com.example.akinms.ui.bodega.products.ProductsViewModel
import com.example.akinms.ui.theme.PrimaryColor
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MapsScreen(
    navController: NavHostController,
    idCliente: Long,
    mapsViewModel: MapsViewModel = hiltViewModel()
){
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
            modifier = Modifier.fillMaxWidth(1f).background(PrimaryColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "", tint = Color.White)
            }
            Text(text = "Encuentra tu bodega cercana", color = Color.White,fontWeight = FontWeight.Bold,)
        }
        MapContainer(
            navController = navController,
            isLoading = state.isLoading,
            bodegas = state.bodegas,
            idCliente = idCliente
        )
    }
}