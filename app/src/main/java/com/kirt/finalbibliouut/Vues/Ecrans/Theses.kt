package com.kirt.finalbibliouut.Vues.Ecrans

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.kirt.finalbibliouut.Vues.Composants.ListThese
import com.kirt.finalbibliouut.Vues.Composants.SearchBack
import com.kirt.finalbibliouut.Vues.Composants.TheseName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

@Composable
@Preview(showBackground = true)
fun Thesespreview()
{
    val application = Application()
    val scope = rememberCoroutineScope()
    val viewModel = LoginViewModel(application, scope)
    val scaffoldState= rememberScaffoldState()
    val pd : PaddingValues= PaddingValues()
    val na = rememberNavController()
    Theses(viewModel,na,pd,scaffoldState,scope)

}

@Composable
fun Theses(viewModel: LoginViewModel,
           navController: NavController,
           pd: PaddingValues,
           scaffoldState: ScaffoldState,
           scope : CoroutineScope
)
{

    LaunchedEffect(key1 = Unit)
    {
        scope.async {

            viewModel.ChargeThese()
        }

    }

    fun navigate()
    {
        navController.popBackStack()
    }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column(modifier = Modifier.size(screenWidth, screenHeight)) {
        SearchBack(scaffoldState,scope,navController, ::navigate, Ecrans.These.destination, viewModel)
        TheseName("Thèses et Mémoires",navController,Ecrans.These.destination)
        ListThese(viewModel._LisThese.value,scope, viewModel)
    }

}