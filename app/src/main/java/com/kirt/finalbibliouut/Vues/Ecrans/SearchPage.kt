package com.kirt.finalbibliouut.Vues.Ecrans

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import com.kirt.finalbibliouut.Vues.Composants.ListBook
import com.kirt.finalbibliouut.Vues.Composants.ListBookRecherche
import com.kirt.finalbibliouut.Vues.Composants.SearchBar

@Composable
@Preview(showBackground = true)
fun SearchPagepreview()
{
    val na = rememberNavController()
    val scope = rememberCoroutineScope()
    val application = Application().applicationContext
    var vm =LoginViewModel(application, scope)
    SearchPage(viewModel = vm, na)
 
}

@Composable
fun SearchPage(viewModel: LoginViewModel, navController: NavController)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column (modifier = Modifier.size(screenWidth,screenHeight)){
        SearchBar(viewModel,navController )
        ListBookRecherche(lisbk = viewModel._rebook.value, navController)

    }

}