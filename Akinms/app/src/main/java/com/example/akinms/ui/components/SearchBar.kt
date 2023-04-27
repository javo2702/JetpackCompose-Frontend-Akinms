package com.example.akinms.ui.components

import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.akinms.R
import com.example.akinms.ui.bodega.products.ProductsViewModel
import com.example.akinms.util.navigationGraph.BodegaScreen
import com.example.akinms.util.navigationGraph.Graph

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    navController: NavHostController,
    name:String,
    idBodega: Int,
    productViewModel: ProductsViewModel = hiltViewModel()
){
    var textSearch by remember { mutableStateOf("") }
    val result = productViewModel.state
    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
            .clip(shape = RoundedCornerShape(8.dp))
    ){
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE5E5E5))
                    .padding(start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val (focusRequester) = FocusRequester.createRefs()
                Icon(painter = painterResource(R.drawable.search), contentDescription = "Search Icon" )
                BasicTextField(
                    value = textSearch,
                    singleLine = true,
                    onValueChange = {
                        textSearch = it
                        println("TEXTO INGRESADO :       "+textSearch)},
                    decorationBox = {
                            innerTextField ->
                        Row(
                        ) {
                            if (textSearch.isEmpty()) {
                                Text(
                                    text ="Buscar en minimarket $name",
                                    color = Color(0xFFA8A8A8)
                                )
                            }
                            innerTextField()  //<-- Add this
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
                    keyboardActions = KeyboardActions(
                        onGo = {
                            navController.navigate(BodegaScreen.Products.route+"/buscar/"+textSearch)
                        }
                    ),
                    modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 10.dp)
                        .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER){
                            navController.navigate(BodegaScreen.Products.route+"/buscar/"+textSearch)
                            true
                        }
                        false
                    }

                )
            }
        }

    }
}