package com.example.akinms.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.akinms.domain.model.CartItem
import com.example.akinms.domain.model.Products
import com.example.akinms.ui.bodega.cart.CartViewModel
import com.example.akinms.util.navigationGraph.BodegaScreen

@Composable
fun CompleteProductDialog(
    dialogState: MutableState<Boolean>,
    content: @Composable () -> Unit,
){
    Card(
        modifier = Modifier
            .width(270.dp)
            .height(305.dp)
            .border(width = 1.dp, color = Color(0xFFFFC532), shape = RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column() {
            Box(
                modifier = Modifier
            ){
                TitleAndButton(dialogState = dialogState)
                AddBody(content)
            }
        }

    }
}
@Composable
private fun TitleAndButton(dialogState: MutableState<Boolean>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .zIndex(10f),
        horizontalArrangement = Arrangement.End,
    ) {
        IconButton(modifier = Modifier.then(Modifier.size(24.dp)),
            onClick = {
                dialogState.value = false
            }) {
            Icon(
                tint = Color.White,
                imageVector = Icons.Filled.Close,
                contentDescription = "contentDescription"
            )
        }
    }
}

@Composable
private fun AddBody(content: @Composable () -> Unit) {
    content()
}

@Composable
fun BodyProductDialogContent(
    producto: Products,
    cantidad: MutableState<Int>,
    navController: NavHostController,
    context: Context,
    idBodega: Int,
    cartViewModel: CartViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(163.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color(0xFFFFC532))

        ){
            val indice:Int = LocalContext.current.resources.getIdentifier(producto.img,"drawable",
                LocalContext.current.packageName)
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = rememberAsyncImagePainter(producto.img),
                contentDescription = null,
                //modifier = Modifier.size(100.dp)
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProductTextDetails(producto = producto)
            IncDecBtn2(cantidad = cantidad, context = context)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ){
            Button(
                onClick = {
                    //Guardar el producto en room
                    //val item = CartItemEntity(producto.name,producto.descripcion,producto.precio,producto.img,cantidad.value)
                    cartViewModel.addCartItem(
                        CartItem(
                            id = 0,
                            idproducto = producto.id,
                            nombre = producto.nombre,
                            descripcion = producto.descripcion,
                            precio = producto.precio,
                            cantidad = cantidad.value,
                            img = producto.img,
                            idBodega = idBodega
                        )
                    )
                    println("Enviar producto")
                    navController.navigate(BodegaScreen.Cart.route+"/"+idBodega)
                },
                modifier = Modifier.width(240.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF52CA73),
                    contentColor = Color(0xFFFFFFFF)
                ),
            ) {
                Text(text = "Add to Cart", fontSize = 20.sp)
            }
        }
    }
}