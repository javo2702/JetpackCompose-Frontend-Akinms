package com.example.akinms.ui.components

import com.example.akinms.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.akinms.domain.model.Bodega
import com.example.akinms.domain.model.CartItem
import com.example.akinms.ui.bodega.cart.CartViewModel
import com.example.akinms.util.navigationGraph.BodegaScreen

@Composable
fun NavBarTop(
    bodega:Bodega,
    navController: NavHostController,
    coreController: NavHostController,
    cartViewModel: CartViewModel = hiltViewModel(),
    //cantidad: MutableState<Int>
){
    val cartItems by cartViewModel.items.collectAsState(initial = emptyList())
    var cartBodega = mutableListOf<CartItem>()
    for(item in cartItems){
        if(item.idBodega == bodega.id)
            cartBodega.add(item)
    }
    var cantidad by remember { mutableStateOf("") }
    cantidad = cartBodega.size.toString()
    /*var cantidad2 = 0
    var cantidad = remember { mutableStateOf(cantidad2) }
    //var minimarket: Minimarket = Minimarket()
    val items by cartViewModel.items.collectAsState(initial = emptyList())
    var cartBodega = mutableListOf<CartItem>()
    for(item in items){
        if(item.idBodega == bodega.id)
            cartBodega.add(item)
    }
    cantidad2 = cartBodega.size
    cantidad.value = cartBodega.size
    println("CANTIDAD CARRITO BODEGA "+bodega.nombre+" cantidad: "+cantidad2)*/
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .background(Color(0xFFFFFFFF))
                .shadow(
                    elevation = .6.dp,
                    shape = RoundedCornerShape(10.dp),
                )
        ) {
            IconButton(onClick = {
                if(navController.currentDestination?.route!="BODEGAMAIN"){
                    navController.popBackStack()
                }
                else{
                    coreController.popBackStack()
                }


            }) {
                Icon(
                    modifier = Modifier
                        .height(18.dp),
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "back"
                )
            }
        }

        Box(){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.map_marker), contentDescription = "map"
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = bodega.direccion,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = bodega.nombre,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .background(Color(0xFFFFFFFF))
                .shadow(
                    elevation = .6.dp,
                    shape = RoundedCornerShape(10.dp),
                )
        ) {
            IconButton(onClick = { navController.navigate(BodegaScreen.Cart.route+"/"+bodega.id) }) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(18.dp),
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "cart")
            }
            Box(
                modifier = Modifier
                    .padding(top = 4.dp, end = 4.dp)
                    .width(10.dp)
                    .height(10.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary)
                    .align(Alignment.TopEnd)

            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = cantidad,
                    color = Color.White,
                    fontSize = 8.sp,
                )
            }
        }
    }
}