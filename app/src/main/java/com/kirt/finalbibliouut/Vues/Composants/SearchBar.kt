package com.kirt.finalbibliouut.Vues.Composants

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.ViewModel.LoginViewModel

fun pas()
{

}

@Composable
@Preview(showBackground = true)
fun SearchBarpreview()
{
    val application = Application().applicationContext
    val scope = rememberCoroutineScope()
    val vm = LoginViewModel(application, scope)
    val na = rememberNavController()
    SearchBar(vm,na)
}

@Composable
fun SearchBar(viewModel: LoginViewModel,navController: NavController)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    fun navigate()
    {
        navController.popBackStack()
    }
    Card(elevation = 8.dp,
        modifier = Modifier
            .padding(6.dp)


            .size(screenWidth, 50.dp),
        shape = RoundedCornerShape(50.dp)
    ) {
        Row( modifier = Modifier.fillMaxWidth() )
        {

                            IconButton(onClick = { navigate() },
                                modifier = Modifier.padding(2.dp))
                            {
                                Icon(
                                    Icons.Default.ArrowBack,
                                    contentDescription = "retour",
                                    modifier = Modifier.size(ButtonDefaults.IconSize).weight(0.1f)
                                )
                            }





                            val texti = remember{ mutableStateOf("")}


                            TextField(value = texti.value,
                                onValueChange = {texti.value = it
                                                viewModel.Rechercher(it)
                                                },

                                colors= TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                                modifier = Modifier.weight(0.2f)


                            )
        }



        }
    }
