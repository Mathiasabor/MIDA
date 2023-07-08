package com.kirt.finalbibliouut.Vues.Composants

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.LoginActivity
import com.kirt.finalbibliouut.R
import com.kirt.finalbibliouut.Routes.Ecrans
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun a()
{

}
@Composable
@Preview(showBackground = true)
fun MenuIdenBoxpreview()
{
    val application = Application().applicationContext
    val na = rememberNavController()
    val scope = rememberCoroutineScope()
    val viewModel = LoginViewModel(application, scope)
    val sc = rememberScaffoldState()

MenuIdenBox(viewModel,na, ::a, sc, scope)
}


@Composable
fun MenuIdenBox(viewModel: LoginViewModel, navController: NavController, onLogout: ()->Unit, scaffoldState: ScaffoldState, scope :CoroutineScope)
{
    val ccolor = Color(136,0,27)
    val neon = Color(255, 32, 121)
    var drawerState = scaffoldState.drawerState
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(screenHeight)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())

    )
    {
        Card(modifier = Modifier
            .width(300.dp)
            .padding(16.dp), elevation = 8.dp, shape = RoundedCornerShape(30.dp)) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(300.dp)) {
                Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.width(300.dp)) {

                    Image(painter = painterResource(R.drawable.user),
                        contentDescription = "",
                        modifier =Modifier
                            .clip(shape = CircleShape))
                }
                Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.width(300.dp)) {
                    Text(text = "${viewModel._infoUsers.value.first_name} ${viewModel._infoUsers.value.last_name}", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                }
                Text(text = "${viewModel._infoUsers.value.username} ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0,148,243))

                Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.width(300.dp)) {
                    Text(text = "Connecté", fontSize = 18.sp)
                }

            }

        }


        ExtendedFloatingActionButton(
            text = {
                Row(modifier = Modifier.width(200.dp)) {

                    Text(text = "Accueil")
                }

            },
            onClick = {
                navController.navigate(Ecrans.Accueil.destination)

                scope.launch { if (drawerState.isClosed)
                    drawerState.open() else drawerState.close() }

            },
            icon = { Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "")
            },

            modifier = Modifier.padding(0.dp,0.dp,0.dp,16.dp)

        )

        ExtendedFloatingActionButton(
            text = {
                Row(modifier = Modifier.width(200.dp)) {

                    Text(text = "Livres consultés")
                }

            },
            onClick = {
                navController.navigate(Ecrans.Consultes.destination){
                popUpTo(Ecrans.Accueil.destination)

            }
                scope.launch { if (drawerState.isClosed)
                    drawerState.open() else drawerState.close() }

                      },
            icon = { Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "")
            },

            modifier = Modifier.padding(0.dp,0.dp,0.dp,16.dp)

            )

        ExtendedFloatingActionButton(

            text = {
                Row(modifier = Modifier.width(200.dp)) {

                    Text(text = "Thèses")
                }

            },
            onClick = {

                navController.navigate(Ecrans.These.destination){
                    popUpTo(Ecrans.Accueil.destination)

                }

                scope.launch { if (drawerState.isClosed)
                    drawerState.open() else drawerState.close() }
            },
            icon = { Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "")
            },

            modifier = Modifier.padding(0.dp,0.dp,0.dp,16.dp)

            )

        ExtendedFloatingActionButton(
            text = {
                Row(modifier = Modifier.width(200.dp)) {

                    Text(text = "Téléchargés")
                }

            },
            onClick = {

                navController.navigate(Ecrans.Telecharge.destination){
                    popUpTo(Ecrans.Accueil.destination)

                }

                scope.launch { if (drawerState.isClosed)
                    drawerState.open() else drawerState.close() }


            },
            icon = { Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "")
            },
            modifier = Modifier.padding(0.dp,0.dp,0.dp,16.dp)


            )

        ExtendedFloatingActionButton(
            text = {
                Row(modifier = Modifier.width(200.dp)) {

                    Text(text = "Déconnexion")
                }

            },
            onClick = {onLogout() },
            icon = { Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "")
            },
            modifier = Modifier.padding(0.dp,0.dp,0.dp,16.dp)



            )









    }

}