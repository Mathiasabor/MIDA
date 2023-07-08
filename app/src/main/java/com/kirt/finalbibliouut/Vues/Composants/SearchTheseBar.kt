package com.kirt.finalbibliouut.Vues.Composants

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kirt.finalbibliouut.ViewModel.LoginViewModel

@Composable
fun SearchTheseBar(viewModel: LoginViewModel, navController: NavController)
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





            val texti = remember{ mutableStateOf("") }


            TextField(value = texti.value,
                onValueChange = {texti.value = it
                    viewModel.TRechercher(it)
                },

                colors= TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                modifier = Modifier.weight(0.2f)


            )
        }



    }
}