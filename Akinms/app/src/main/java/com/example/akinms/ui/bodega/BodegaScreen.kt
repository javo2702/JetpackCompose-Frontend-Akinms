package com.example.akinms.ui.bodega
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import com.example.akinms.util.navigationGraph.BodegaScreen.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.akinms.util.navigationGraph.BodegaScreen
import com.example.akinms.components.*
import com.example.akinms.domain.model.CartItem
import com.example.akinms.domain.model.Categoria
import com.example.akinms.ui.Maps.MapsViewModel
import com.example.akinms.ui.bodega.cart.CartScreen
import com.example.akinms.ui.bodega.cart.CartViewModel
import com.example.akinms.ui.bodega.categories.CategoriesScreen
import com.example.akinms.ui.bodega.pedido.CheckOutScreen
import com.example.akinms.ui.bodega.products.ProductsScreen
import com.example.akinms.ui.components.*
import com.example.akinms.util.navigationGraph.Graph
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BodegaScreen(
    coreNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
    bodegaViewModel: BodegaViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    id: Long,
    id_cli: Long
) {
    val items by cartViewModel.items.collectAsState(initial = emptyList())
    var cartBodega = mutableListOf<CartItem>()
    for(item in items){
        if(item.idBodega == id.toInt())
            cartBodega.add(item)
    }
    val state = bodegaViewModel.state
    val eventFlow = bodegaViewModel.eventFlow
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
    val bodega = state.bodega
    val categorias = state.categorias
    val someCategorias = mutableListOf<Categoria>()
    for(cat in categorias){
        if(someCategorias.size<6){
            someCategorias.add(cat)
        }
    }
    if(state.isLoading){
        FullScreenLoading()
    } else{
        if(bodega!=null){
            Scaffold(
                topBar = {
                    NavBarTop(bodega = bodega, navController = navController, coreController = coreNavController)
                },
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Bodega.route,
                    route = Graph.BODEGA+"/cliente/{id_cli}/{id}"
                ){
                    composable(route = Bodega.route){
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            content = {
                                item {
                                    SearchBar(
                                        navController = navController,
                                        name = bodega.nombre,
                                        idBodega = bodega.id
                                    )
                                }
                                item {
                                    SomeCategories(navController = navController, category = someCategorias, idBodega = bodega.id)
                                }
                                item {
                                    Button(
                                        modifier = Modifier
                                            .padding(top = 20.dp, bottom = 10.dp)
                                            .fillMaxWidth(.9f),
                                        onClick = { navController.navigate(Products.route+"/"+bodega.id) }
                                    ) {
                                        Text(text = "Ver Catalogo de productos    ")
                                        Image(painter = painterResource(id = com.example.akinms.R.drawable.dairy), contentDescription = "")
                                    }
                                }
                                item {
                                    var ruta = Products.route+"/"+bodega.id
                                    Other(navController = navController, ruta = ruta)
                                }
                            }
                        )
                    }
                    composable(route = BodegaScreen.Categories.route){
                        CategoriesScreen(navController = navController, bodegaNombre = bodega.nombre, listaCategorias = categorias, idBodega = bodega.id)
                    }
                    composable(
                        route = BodegaScreen.Products.route+"/{id}",
                        arguments = listOf(navArgument("id"){type= NavType.LongType})
                    ) {
                        ProductsScreen(navController = navController,bodegaNombre = bodega.nombre, idBodega = bodega.id, categorias = categorias)
                    }
                    composable(route = BodegaScreen.Cart.route+"/{id}",
                        arguments = listOf(navArgument("id"){type= NavType.LongType})) {
                        CartScreen(navController = navController, idBodega = bodega.id)
                    }
                    composable(
                        route = BodegaScreen.Products.route+"/bodega/{id1}/categoria/{id2}",
                        arguments = listOf(
                            navArgument("id1"){type= NavType.LongType},
                            navArgument("id2"){type= NavType.LongType}
                        )
                    ) {
                        ProductsScreen(navController = navController,bodegaNombre = bodega.nombre,idBodega=bodega.id, categorias = categorias, filterCategory = true)
                    }
                    composable(
                        route = BodegaScreen.Products.route+"/buscar/{nombre}",
                        arguments = listOf(
                            navArgument("nombre"){type= NavType.StringType},
                        )
                    ) { backStackEntry ->
                        val nombre = backStackEntry.arguments?.getString("nombre")
                        requireNotNull(nombre)
                        ProductsScreen(navController = navController,bodegaNombre = bodega.nombre,idBodega=bodega.id, categorias = categorias, nombreSearch = true, nombre = nombre)
                    }
                    composable(
                        route = BodegaScreen.CheckOut.route
                    ){
                        CheckOutScreen(navController=navController, idBodega = bodega.id, idCliente = id_cli.toInt())
                    }
                }

            }
        }
    }

}
