package com.example.akinms.ui.profile.historial

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.akinms.R
import com.example.akinms.ui.profile.ProfileState
import com.example.akinms.ui.profile.ProfileViewModel
import com.example.akinms.ui.theme.PrimaryColor
import com.example.akinms.util.navigationGraph.HistorialScreen
import org.w3c.dom.Text

@Composable
fun HistorialScreen(
    navController: NavHostController,
    historialViewModel: HistorialViewModel = hiltViewModel()
){
    var state: HistorialState = historialViewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
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
            Text(text = "Historial de Compras", color = Color.White,fontWeight = FontWeight.Bold,)
        }
        Row (
            modifier = Modifier
                .padding(top = 10.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                "Compras",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            )
            HistText(text = state.pedidos.size.toString()+" Compras",13, FontWeight.Bold, Color.Gray)
        }
        Divider(startIndent = 8.dp, thickness = 0.5.dp, color = Color.LightGray)
        LazyColumn(
            modifier = Modifier
                .padding(top = 2.dp)
        ) {
            item {
                for(pedido in state.pedidos) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(5))
                            .border(BorderStroke(1.dp, PrimaryColor),
                                shape = RoundedCornerShape(5))
                            .background(Color.White)
                            .padding(3.dp)

                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Column() {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = pedido.bodega.direccion+" - "+pedido.bodega.nombre,
                                        fontWeight = FontWeight.Bold
                                    )
                                    if (pedido.estado == "enviado") {
                                        Text(
                                            text = pedido.estado,
                                            fontSize = 13.sp,
                                            color = Color.Green,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }else{
                                        Text(
                                            text = pedido.estado,
                                            fontSize = 13.sp,
                                            color = PrimaryColor,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                    IconButton(
                                        onClick = { navController.navigate(HistorialScreen.Details.route+"/cliente/"+1+"/pedido/"+pedido.idpedido.toLong()) }
                                    ) {
                                        Icon(
                                            Icons.Rounded.KeyboardArrowRight,
                                            contentDescription = "arrow right",
                                            tint = Color.Black
                                        )
                                    }

                                }
                                Row() {
                                    Text(
                                        text = pedido.fecha,
                                        fontSize = 12.sp,
                                        color = Color.Gray,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .padding(
                                        top = 10.dp,
                                        bottom = 10.dp
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                var i: Int = 1
                                for(detalle in pedido.detallesPedido){
                                    if (i <= 5){
                                        Image(
                                            modifier = Modifier
                                                .height(40.dp)
                                                .weight(40f),
                                            painter = rememberAsyncImagePainter(detalle.producto.img),
                                            contentDescription = null,
                                            //modifier = Modifier.size(100.dp)
                                        )
                                    }
                                    else{
                                        break
                                    }
                                    i++
                                }

                                if(pedido.detallesPedido.size > 6){
                                    var cantProdExtra: Int = pedido.detallesPedido.size - 5
                                    Text(
                                        "+ "+cantProdExtra.toString(),
                                        modifier = Modifier
                                            .height(25.dp)
                                            .width(30.dp)
                                            .align(Alignment.CenterVertically)
                                            .clip(RoundedCornerShape(20))
                                            .background(Color.LightGray)
                                        ,
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    HistText("Valoracion",13, FontWeight.Bold, Color.Gray)
                                    HistText("* * * * *",16, FontWeight.Bold, color = PrimaryColor)
                                }

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    HistText("Productos",13, FontWeight.Bold, Color.Gray)
                                    HistText(text=pedido.detallesPedido.size.toString()+" pedidos",12, FontWeight.Bold)
                                }

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    HistText("Precio de Compra",13, FontWeight.Bold, Color.Gray)
                                    HistText(text = "S/. "+pedido.monto_total.toString(),12, FontWeight.Bold)
                                }

                            }

                        }

                    }
                }
            }
        }
    }

}
@Composable
fun HistText (text: String, size: Int = 13, fontWeight: FontWeight = FontWeight.Normal, color: Color = Color.Black) {
    Text(
        text = text,
        color = color,
        fontSize = size.sp,
        fontWeight = fontWeight,
        textAlign = TextAlign.Center
    )
}