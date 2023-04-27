package com.example.akinms.ui.bodega.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.akinms.domain.model.CartItem
import com.example.akinms.ui.bodega.cart.components.CartProductItem
import com.example.akinms.ui.bodega.cart.components.CartTotalResume
import com.example.akinms.util.navigationGraph.BodegaScreen

@Composable
fun CartScreen(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel(),
    idBodega: Int,
){
    val cartItems by viewModel.items.collectAsState(initial = emptyList())

    var cartBodega = mutableListOf<CartItem>()
    for(item in cartItems){
        if(item.idBodega == idBodega)
            cartBodega.add(item)
    }
    Scaffold(
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                item{
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        text = "Carrito de Compras",
                        textAlign = TextAlign.Center,
                        color = Color(0xFF03AC00),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                if(cartBodega.size>0){
                    var total = 0.0
                    cartBodega.forEach {
                        total += it.precio*it.cantidad
                        item {
                            CartProductItem(item = it, viewModel = viewModel,navController=navController,idBodega)
                        }
                    }

                    item{
                        CartTotalResume(total = total)
                    }
                    item{
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Button(
                                modifier = Modifier.width(200.dp),
                                onClick = { navController.navigate(BodegaScreen.CheckOut.route) }
                            ) {
                                Text(text = "Continuar CheckOut")
                            }
                        }

                    }
                }
                else{
                    item {
                        Text(
                            text = "Carrito Vacio"
                        )
                    }
                }

            }
        )
    }
}