package com.example.akinms.ui.login



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment

import com.example.akinms.ui.theme.AkinmsTheme

import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image

import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.runtime.Composable

import androidx.compose.ui.layout.ContentScale

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.draw.paint

import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


import com.example.akinms.ui.theme.Shapes
import com.example.akinms.R
import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.ui.theme.PrimaryColor
import com.example.akinms.util.navigationGraph.Graph
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait


@Composable
fun LoginView(
    onClick: () -> Unit,
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) = AkinmsTheme {
    var state = loginViewModel.state
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    var id: Long = -1
    val botonAzul = Color(0xff100DB1)
    var correo = remember{ mutableStateOf("") }
    var password = remember{ mutableStateOf("") }

    val showDialog = remember{ mutableStateOf(false) }
    val showDialogError = remember{ mutableStateOf(false) }
    if(state.cliente!=null){
        if(state.cliente!!.idcliente!=-1){
            id = state.cliente!!.idcliente.toLong()
            showDialog.value = true
        } else{
            state.cliente = null
            showDialogError.value = true
        }

    }
    if(showDialog.value){
        Alert(
            texto = "Inicio de Sesion Exitoso",
            msg="Continuar",
            imageId = R.drawable.checked,
            showDialog = showDialog.value,
            onDismiss = {showDialog.value = false},
            onLoginClick = {
                navController.popBackStack()
                navController.navigate(Graph.HOME+"/"+id)
            }
        )
    }
    if(showDialogError.value){
        Alert(
            texto = "Error al iniciar sesión: Ingrese correo y contraseña validos",
            msg="Volver",
            imageId = R.drawable.cancelar,
            showDialog = showDialogError.value,
            onDismiss = {showDialogError.value = false},
            onLoginClick = { showDialogError.value = !showDialogError.value }
        )
    }
    Box(
        Modifier
            .paint(painterResource(id = R.drawable.login_bg), contentScale = ContentScale.FillWidth)
            .fillMaxSize(),
    )
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        Image(
            painter = painterResource(R.drawable.logo),
            null,
            Modifier
                .padding(top = 60.dp)
                .size(100.dp)
        )

        Text(
            modifier = Modifier.padding(top = 65.dp),
            text = "INICIO DE SESION", color=Color.Black, textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold, fontSize = 22.sp)

        //Input email
        OutlinedTextField(
            modifier = Modifier
                .height(60.dp)
                .width(300.dp),
            value = correo.value, onValueChange = {correo.value = it},
            leadingIcon = { Icon(imageVector = InputType.Name.icon, null, tint = PrimaryColor)},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = PrimaryColor,
                unfocusedIndicatorColor = PrimaryColor,
                disabledIndicatorColor = Color.Black,
                placeholderColor = Color.Gray,
                focusedLabelColor = Color.Black
            ),
            label = {Text("Correo", fontSize = 15.sp)},
            textStyle = TextStyle(fontSize = 14.sp),
            singleLine = true,
            keyboardOptions = InputType.Name.keyboardOptions,
            visualTransformation = InputType.Name.visualTransformation,
            keyboardActions =   KeyboardActions(onNext = {

            }),
        )

        //Input contraseña
        OutlinedTextField(
            modifier = Modifier
                .height(60.dp)
                .width(300.dp),
            value = password.value, onValueChange = {password.value = it},
            leadingIcon = { Icon(imageVector = InputType.Password.icon, null, tint = PrimaryColor)},
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = PrimaryColor,
                unfocusedIndicatorColor = PrimaryColor,
                disabledIndicatorColor = Color.Black,
                placeholderColor = Color.Gray,
                focusedLabelColor = Color.Black
            ),
            label = {Text("Contraseña", fontSize = 15.sp)},
            textStyle = TextStyle(fontSize = 14.sp),
            singleLine = true,
            keyboardOptions = InputType.Password.keyboardOptions,
            visualTransformation = InputType.Password.visualTransformation,
            keyboardActions =   KeyboardActions(onDone = {

            }),
        )

        //Boton Ingresar
        Button(
            onClick = {
                //
                var cliente: Cliente = Cliente("","","","",correo.value,password.value,-1)
                loginViewModel.buscarCliente(cliente)
            }, modifier = Modifier
                .height(50.dp)
                .width(250.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor,
                contentColor = Color.White)){
            Text("Ingresar", Modifier.padding(vertical = 2.dp))
        }

        //Recuperar contraseña
        Text("¿No recuerda su contraseña?", color = PrimaryColor)

        //Crear cuenta
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("¿No tiene una cuenta?", color = Color.Black)
            Text("  Crear Cuenta", color = PrimaryColor)
        }


        Divider(
            color = Color.White.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 15.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically, modifier =  Modifier.padding(bottom = 30.dp)) {
            Text("2022. Squad Armonia 10", color = Color.Black)
        }
    }
    //}
}


sealed class InputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
){
    object Name : InputType(
        label = "Email",
        icon = Icons.Default.Person,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        visualTransformation = VisualTransformation.None
    )

    object Password : InputType(
        label = "Contraseña",
        icon = Icons.Default.Lock,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun Alert(
    texto: String,
            msg : String,
    imageId: Int,
          showDialog: Boolean,
          onDismiss: () -> Unit,
          onLoginClick: () -> Unit
) {

    if (showDialog) {
        val colorAlerta = Color(0xFF70D68C)
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onLoginClick ) {
                    Text(msg, color = PrimaryColor, textAlign = TextAlign.Right)
                }
            },
            title={
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    //Icon(Modifier.size(26.dp),
                    //painterResource(R.drawable.check_icon))
                    Image(
                        painterResource(id = imageId),
                        contentDescription = null,
                        Modifier
                            .align(Alignment.Center)
                            .size(70.dp, 70.dp)
                    )
                }
            },
            text = {
                if(texto.contains("Error")){
                    Text(text = texto,
                        textAlign = TextAlign.Center,
                        color = Color(0xFFBB2424), fontWeight = FontWeight.SemiBold, fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth())
                } else{
                    Text(text = texto,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF32BA7C), fontWeight = FontWeight.SemiBold, fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth())
                }

            },
            backgroundColor = Color.White,
            shape = RoundedCornerShape(5.dp)


        )
    }
}