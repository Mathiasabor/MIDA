package com.kirt.finalbibliouut.Vues.Ecrans

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.Routes.Ecrans
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import com.kirt.finalbibliouut.Vues.Composants.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

@Composable
@Preview(showBackground = true)
fun Accueilpreview()
{
    val scaffoldState= rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val pd : PaddingValues= PaddingValues()
    val na = rememberNavController()
    val application = Application().applicationContext

    val viewModel = LoginViewModel(application, scope)
    Accueil(viewModel, na,pd,scaffoldState,scope)
}
@Composable
fun Accueil(viewModel: LoginViewModel, navController: NavController,pd :PaddingValues, scaffoldState: ScaffoldState, scope : CoroutineScope)
{
    var destination = Ecrans.Accueil.destination
   LaunchedEffect(key1 = Unit)
    {
        scope.async {
            viewModel.ChargeLivres()
            viewModel.ChargeCategories()

        }
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column (modifier =Modifier.size(screenWidth,screenHeight)){
        SearchAccueil(scaffoldState, scope , navController, destination)

       // BlocAccueil()

        AccueilName("Listes des catégories")
        listBookCategorie(cat = viewModel.bookcat.value,viewModel,navController)

    }
}