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
import com.kirt.finalbibliouut.Vues.Composants.BackBar
import com.kirt.finalbibliouut.Vues.Composants.ListBook
import com.kirt.finalbibliouut.Vues.Composants.ListBookConsultes
import com.kirt.finalbibliouut.Vues.Composants.SearchBack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

@Composable
@Preview(showBackground = true)
fun Consultespreview()
{
    val scope = rememberCoroutineScope()
    val application = Application()
    val vm = LoginViewModel(application, scope)
    val nv = rememberNavController()
    val sc = rememberScaffoldState()

    Consultes(vm,nv,sc,scope)
}

@Composable
fun Consultes(viewModel: LoginViewModel,navController: NavController, scaffoldState: ScaffoldState, scope : CoroutineScope)
{
    LaunchedEffect(key1 = Unit)
    {
        scope.async {
            viewModel.ChargeLivres()
        viewModel.LivreConsulte()
        }}
    fun navigate()
    {
        navController.popBackStack()
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column (modifier = Modifier.size(screenWidth,screenHeight)){
        SearchBack( scaffoldState, scope,navController, ::navigate, Ecrans.Consultes.destination, viewModel)
        AccueilName(name ="Livres Consultés")
        ListBookConsultes(lisbk = viewModel._Consultbook.value,navController,viewModel)
    }
}