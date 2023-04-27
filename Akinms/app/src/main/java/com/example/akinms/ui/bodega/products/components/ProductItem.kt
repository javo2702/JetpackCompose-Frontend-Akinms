package com.example.akinms.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.akinms.domain.model.Products
import com.example.akinms.ui.components.BodyProductDialogContent
import com.example.akinms.ui.components.CompleteProductDialog
import com.example.akinms.ui.components.ProductTextDetails


@Composable
fun ProductItem(
    producto: Products,
    idBodega: Int,
    navController: NavHostController
){
    val context: Context = LocalContext.current
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    if(dialogState.value){
        var cantidad = remember {
            mutableStateOf(1)
        }
        Dialog(
            onDismissRequest = { dialogState.value = false },
            content = {
                CompleteProductDialog(dialogState){
                    BodyProductDialogContent(cantidad= cantidad,producto = producto, navController = navController, context = context,idBodega=idBodega)
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
    Column(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxHeight()
            .width(160.dp)
            .border(width = 1.dp, color = Color(0xFFFFC532), shape = RoundedCornerShape(10.dp)),
    ) {
        Box(
            modifier = Modifier
                .height(98.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color(0xFFFFC532))
                //.align(Alignment.CenterHorizontally)

        ){
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = rememberAsyncImagePainter(producto.img),
                contentDescription = null,
                //modifier = Modifier.size(100.dp)
            )
        }
        ProductTextDetails(producto = producto)
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF52CA73),
                contentColor = Color(0xFFFFFFFF)
            ),
            onClick = { dialogState.value = true }
        ) {
            Text(text = "Agregar")
        }
    }
}