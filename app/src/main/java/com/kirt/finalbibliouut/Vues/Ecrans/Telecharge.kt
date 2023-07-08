package com.kirt.finalbibliouut.Vues.Ecrans

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.Routes.Ecrans
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import com.kirt.finalbibliouut.Vues.Composants.AccueilName
import com.kirt.finalbibliouut.Vues.Composants.ListTelech
import com.kirt.finalbibliouut.Vues.Composants.SearchBack
import com.kirt.finalbibliouut.Vues.Composants.TheseName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

@Composable
@Preview(showBackground = true)
fun Telechargepreview()
{
    val na = rememberNavController()
    val application = Application().applicationContext
    val scope = rememberCoroutineScope()
    val vm = LoginViewModel(application, scope)
    val sc = rememberScaffoldState()
    Telecharge(vm,na, sc,scope)
}
@Composable
fun Telecharge(viewModel :LoginViewModel, navController: NavController, scaffoldState: ScaffoldState, scope : CoroutineScope)
{

    LaunchedEffect(key1 = Unit)
    {
        scope.async {
            viewModel.ChargeThese()
            viewModel.ChargeTelech()
        }

    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column (modifier = Modifier.size(screenWidth,screenHeight)){



        fun navigate()
        {
            navController.popBackStack()
        }

        SearchBack( scaffoldState, scope,navController, ::navigate, Ecrans.These.destination, viewModel)
        TheseName(name = " Téléchargées", navController  , Ecrans.Telecharge.destination )
        ListTelech(viewModel._LisTelec.value, viewModel, scope)

    }

}


