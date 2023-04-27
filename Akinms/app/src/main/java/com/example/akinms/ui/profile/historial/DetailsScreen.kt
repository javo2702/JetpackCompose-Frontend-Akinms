package com.example.akinms.ui.profile.historial

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.akinms.R
import com.example.akinms.ui.bodega.cart.components.CartTotalResume
import com.example.akinms.data.source.remote.dto.pedido.Producto
import com.example.akinms.ui.profile.DetalleState
import com.example.akinms.ui.profile.DetalleViewModel
import com.example.akinms.ui.theme.PrimaryColor

@Composable
fun DetailsScreen(
    navController: NavHostController,
    detalleViewModel: DetalleViewModel = hiltViewModel()
){
    var state: DetalleState = detalleViewModel.state
    var pedido = state.pedidos
    var tamanio = state.pedidos?.detallesPedido?.size
    var detalles = state.pedidos?.detallesPedido
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(PrimaryColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "", tint = Color.White)
            }
            Text(
                modifier = Modifier.padding(start = 34.dp),
                text = "Información de Compra",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HistText( pedido?.bodega?.direccion+" - "+pedido?.bodega?.nombre.toString(),18, FontWeight.Bold, Color.Black)
            Row() {
                HistText("ID:",15, FontWeight.Normal, PrimaryColor)
                HistText(pedido?.idpedido.toString(),15, FontWeight.Normal, Color.Black)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 7.dp,horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HistText( pedido?.fecha.toString(),15, FontWeight.Normal, Color.Gray)
            HistText("* * * * *",15, FontWeight.Normal)
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 113.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {

            if(tamanio!=null){
                if(tamanio > 0){
                    var total: Double = 0.0
                    var precio: Double?
                    var cantidad: Int
                    if(detalles!=null){
                        for(detalle in detalles) {
                            precio = detalle.producto.precio
                            cantidad = detalle.cantidad
                            total += precio!! * cantidad

                            item {
                                //CartProductItem(cantidad)
                                println(pedido!!.detallesPedido.size)
                                //CartList(cartList = cartItems)
                                productoDetalle(detalle.producto, cantidad)
                            }

                        }
                        item{
                            CartTotalResume(total = total)
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
        }
    )
}


@Composable
fun productoDetalle(
    item: Producto,
    cantidad: Int
){
    val context: Context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(0.9f)
            .height(105.dp)
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
                    .background(PrimaryColor)

            ){
                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = rememberAsyncImagePainter(item.img),
                    contentDescription = null,
                    //modifier = Modifier.size(100.dp)
                )
            }
            Column(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier.width(240.dp),
                    text = item.nombre!!,
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.width(240.dp),
                    text = item.descripcion!!,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    HistText("S/. "+item.precio.toString(),16, FontWeight.Bold)
                    HistText("Cant: "+cantidad.toString(),16)
                }
            }
        }
    }
}