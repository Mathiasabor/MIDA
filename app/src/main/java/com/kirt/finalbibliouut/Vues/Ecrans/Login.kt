package com.kirt.finalbibliouut.Vues.Ecrans

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.R
import com.kirt.finalbibliouut.Routes.Ecrans
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import com.kirt.finalbibliouut.Vues.Composants.PasswordInput
import com.kirt.finalbibliouut.Vues.Composants.TextInput



private fun Context.doLogin() {
    Toast.makeText(
        this,
        "Something went wrong, try again later!",
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
@Preview(showBackground = true)
fun Loginpreview()
{
    var nv = rememberNavController()
    val scope = rememberCoroutineScope()
    val application = Application()
    val vm = LoginViewModel(application, scope)
    //Login(vm)
}

@Composable
fun Login(viewModel: LoginViewModel, onLogin : ()-> Unit)
{

    var valide : Boolean = false
    val ccolor = Color(136,0,27)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
        .size(screenWidth, screenHeight)

    ) {
        Card(backgroundColor = Color.Blue,shape = CircleShape,
            modifier = Modifier
                .width(screenWidth)
                .height(300.dp)
                .padding(16.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.bibliouutlogocopie),
                contentDescription ="",

                )
        }

Card( modifier = Modifier.padding(16.dp,0.dp,16.dp,0.dp)) {
    Text(text = "Consultez la bibliothèque n'importe où et n'importe quand",
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        textAlign = TextAlign.Center
        )
}

            Spacer(modifier = Modifier.size(30.dp))
        Card(elevation = 8.dp, shape = RoundedCornerShape(30.dp) ,
            modifier = Modifier
                .width(screenWidth)
                .height(700.dp)
                .padding(16.dp)


        ) {

            Column (horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center, modifier = Modifier.padding(2.dp)){


                val passwordFocusRequester = FocusRequester()
                var value by remember { mutableStateOf("") }
                var value1 by remember { mutableStateOf("") }
                Text(text = " Connectez-vous",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic
                    )
                TextField(
                    value = value,
                    onValueChange = { value = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    leadingIcon = { Icon(imageVector = Icons.Default.Person, null, tint =ccolor) },
                    label = { Text(text = "Username") },
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    visualTransformation = VisualTransformation.None,
                    keyboardActions = KeyboardActions(onNext = {
                        passwordFocusRequester.requestFocus()
                    })
                )


                Spacer(modifier = Modifier.size(50.dp))

                val context = LocalContext.current
                val focusManager = LocalFocusManager.current



                val focusRequester = passwordFocusRequester
                TextField(
                    value = value1,
                    onValueChange = { value1 = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusOrder(focusRequester ?: FocusRequester()),
                    leadingIcon = { Icon(imageVector = Icons.Default.Lock, null, tint =ccolor) },
                    label = { Text(text = "Password") },
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    keyboardOptions =  KeyboardOptions(imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()
                       // context.doLogin()
                       // viewModel.Connexion(value, value1)
                        valide = viewModel._bood.value
                        if (valide == true)
                        {
                            onLogin()

                        } else {
                            Toast.makeText(context, "Identifiants invalides", Toast.LENGTH_SHORT).show()}

                    })
                )

                Spacer(modifier = Modifier.size(20.dp))
                Button(onClick = {
                   // viewModel.Connexion(value, value1)
                    valide = viewModel._bood.value
                    if(valide ==true)
                    {
                        onLogin()
                    } else {
                        Toast.makeText(context, "Identifiants invalides", Toast.LENGTH_SHORT).show()}

                    //context.doLogin()
                },colors = ButtonDefaults.buttonColors(Color.Blue), modifier = Modifier.fillMaxWidth()) {
                    Text("SIGN IN", Modifier.padding(vertical = 8.dp), color = Color.White)
                }



            }

        }
    }
}