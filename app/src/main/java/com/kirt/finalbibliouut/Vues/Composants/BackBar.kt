package com.kirt.finalbibliouut.Vues.Composants

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kirt.finalbibliouut.Routes.Ecrans
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
@Preview(showBackground = true)
fun BackBarpreview()
{

    //BackBar()
}

@Composable
fun BackBar(scaffoldState: ScaffoldState, scope :CoroutineScope, navController: NavController)
{

    var drawerState = scaffoldState.drawerState
    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(Color.White),
        modifier = Modifier
            .padding(6.dp)


            .size(500.dp, 50.dp),
        shape = RoundedCornerShape(50.dp)
    ) {
        Row(){

            Row(modifier = Modifier
                .padding(2.dp)

            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "retour",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }

            }
            Spacer(modifier = Modifier.size(10.dp))

            Text(text = "Cliquez pour rechercher", modifier = Modifier.padding(0.dp, 6.dp, 0.dp, 0.dp))

            Spacer(modifier = Modifier.size(75.dp))
            Row(modifier = Modifier
                .padding(2.dp)

            ) {

                IconButton(onClick = { scope.launch { if (drawerState.isClosed)
                    drawerState.open() else drawerState.close() } }
                ) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "compte",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }




        }
    }
}

/*
*
* Box(contentAlignment = Alignment.Center, modifier = Modifier.height(50.dp)) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
*
*
* */

@Composable
@Preview(showBackground = true)
fun SearchBackpreview()
{

    // SearchAccueil()
}

@Composable
fun SearchBack(scaffoldState: ScaffoldState, scope : CoroutineScope, navController: NavController, navigate :()->Unit, destination : String, viewModel: LoginViewModel)
{
    var drawerState = scaffoldState.drawerState
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Button(onClick = { navController.navigate(Ecrans.SearchPage.destination){

        popUpTo(destination)

    } },
        colors = ButtonDefaults.buttonColors(Color.Blue),
        modifier = Modifier
            .padding(6.dp)


            .size(screenWidth, 50.dp),
        shape = RoundedCornerShape(50.dp)
    ) {
        Row( verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()){


                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.height(50.dp)
                        .clickable { navigate()}
                        .clip(CircleShape) ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Search",
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        tint = Color.White
                    )
                }




            Text(text = "Cliquez pour rechercher",
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp, 0.dp, 0.dp, 0.dp),
                overflow = TextOverflow.Ellipsis, color = Color.White)




                IconButton(onClick = { scope.launch { if (drawerState.isClosed)
                    drawerState.open() else drawerState.close() } }
                ) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "compte",
                        modifier = Modifier.size(30.dp),
                        tint = Color.White
                    )
                }





        }
    }
}