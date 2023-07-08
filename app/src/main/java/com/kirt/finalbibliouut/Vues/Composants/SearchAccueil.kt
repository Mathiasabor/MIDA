package com.kirt.finalbibliouut.Vues.Composants

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kirt.finalbibliouut.Routes.Ecrans
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@Preview(showBackground = true)
fun SearchAccueilpreview()
{

   // SearchAccueil()
}

@Composable
fun SearchAccueil(scaffoldState: ScaffoldState, scope : CoroutineScope, navController: NavController, destination: String)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var drawerState = scaffoldState.drawerState

    Button(onClick = {navController.navigate(Ecrans.SearchPage.destination){
        popUpTo(destination)
    }},
        colors = ButtonDefaults.buttonColors(Color.Blue),
        modifier = Modifier
            .padding(6.dp)


            .size(screenWidth, 50.dp),
        shape = RoundedCornerShape(50.dp)
    ) {
Row(verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .fillMaxWidth()){


                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.height(50.dp)) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        tint = Color.White
                    )
                }




             Text(text = "Cliquez pour rechercher",
                 modifier = Modifier.padding(6.dp, 0.dp, 0.dp, 0.dp).weight(1f),
                 overflow = TextOverflow.Ellipsis, color = Color.White
                 )




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
