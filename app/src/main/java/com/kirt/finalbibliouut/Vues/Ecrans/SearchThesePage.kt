package com.kirt.finalbibliouut.Vues.Ecrans

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import com.kirt.finalbibliouut.Vues.Composants.ListBookRecherche
import com.kirt.finalbibliouut.Vues.Composants.ListTheseRecherche
import com.kirt.finalbibliouut.Vues.Composants.SearchBar
import com.kirt.finalbibliouut.Vues.Composants.SearchTheseBar
import kotlinx.coroutines.CoroutineScope

@Composable
fun SearchThesePage(viewModel: LoginViewModel, navController: NavController, scope : CoroutineScope,)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column (modifier = Modifier.size(screenWidth,screenHeight))
    {
        SearchTheseBar(viewModel,navController )
        ListTheseRecherche(lisbk = viewModel._reThese.value, scope, viewModel)

    }
}