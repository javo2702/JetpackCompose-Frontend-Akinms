package com.example.akinms.ui.Maps



import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.akinms.domain.model.Bodega
import com.example.akinms.util.navigationGraph.BodegaScreen
import com.example.akinms.util.navigationGraph.Graph
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapContainer(
    navController: NavHostController,
    isLoading: Boolean = false,
    bodegas: List<Bodega> = emptyList(),
    idCliente: Long
){
    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-12.056085361847803, -77.08451037122089), 17f)
    }

    LaunchedEffect(Unit) {
        multiplePermissionState.launchMultiplePermissionRequest()
    }
    GoogleMap(
        modifier = Modifier.padding(8.dp),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true),
    ){
        GoogleMarkers(bodegas = bodegas, navController = navController,idCliente)
    }
}
@Composable
fun GoogleMarkers(bodegas:List<Bodega>, navController: NavHostController, idCliente: Long) {
    for(bodega in bodegas){
        Marker(
            state = rememberMarkerState(position = bodega.ubicacion),
            title = bodega.nombre,
            icon = BitmapDescriptorFactory.fromResource(com.example.akinms.R.drawable.tienda),
            onClick = {navController.navigate(Graph.BODEGA+"/cliente/"+idCliente+"/"+bodega.id); true}
        )
    }
}