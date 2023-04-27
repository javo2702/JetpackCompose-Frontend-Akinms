package com.example.akinms.ui.bodega.cart.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.akinms.domain.model.CartItem
import com.example.akinms.ui.bodega.cart.CartViewModel
import com.example.akinms.util.navigationGraph.BodegaScreen

@Composable
fun CartProductItem(
    item: CartItem,
    viewModel: CartViewModel,
    navController: NavHostController,
    idBodega: Int,
){
    val context: Context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.9f)
            .height(125.dp)
            .border(width = 1.dp, color = Color(0xFFFFC532), shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(

        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFFFFC532))

            ){

                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = rememberAsyncImagePainter(item.img),
                    contentDescription = null,
                )
            }
            Column(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier.width(100.dp),
                    text = item.nombre,
                    fontSize = 16.sp
                )
                Text(
                    text = item.descripcion,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "S/. "+item.precio.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column(
            modifier = Modifier.padding(end = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            var cantidad = remember { mutableStateOf(item.cantidad) }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Cantidad:")
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .background(Color(0xFFD9D9D9))
                            .clickable {
                                if (cantidad.value > 1) {
                                    cantidad.value--
                                    item.cantidad = cantidad.value
                                    viewModel.updateCartItem(item)
                                    navController.popBackStack()
                                    navController.navigate(BodegaScreen.Cart.route+"/"+idBodega)
                                } else {
                                    Toast
                                        .makeText(
                                            context,
                                            "Cantidad minima es 1",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            }
                    ){
                        Text(modifier = Modifier.align(Alignment.Center), text = "-")
                    }
                    Box(
                        modifier = Modifier
                            .width(38.dp)
                            .height(30.dp)
                    ){
                        Text(modifier = Modifier.align(Alignment.Center), text = cantidad.value.toString())
                    }
                    Box(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .background(Color(0xFFD9D9D9))
                            .clickable {
                                cantidad.value++
                                item.cantidad = cantidad.value
                                viewModel.updateCartItem(item)
                                navController.popBackStack()
                                navController.navigate(BodegaScreen.Cart.route+"/"+idBodega)
                            }
                    ) {
                        Text(modifier = Modifier.align(Alignment.Center), text = "+")
                    }
                }
            }
            IconButton(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .background(Color(0xFFE5383B))
                    .border(width = 1.dp,color=Color(0xFFE5383B), shape = RoundedCornerShape(5.dp))
                    .padding(0.dp),
                onClick = { viewModel.deleteCartItem(item) }
            ) {
                Icon(Icons.Rounded.Delete, contentDescription = "", tint = Color.White)
            }
        }
    }
}