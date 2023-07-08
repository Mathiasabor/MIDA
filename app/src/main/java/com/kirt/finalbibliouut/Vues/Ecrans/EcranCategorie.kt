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
import com.kirt.finalbibliouut.Databases.BookCategory
import com.kirt.finalbibliouut.Databases.Bookdata
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import com.kirt.finalbibliouut.Vues.Composants.AccueilName
import com.kirt.finalbibliouut.Vues.Composants.ListBook
import com.kirt.finalbibliouut.Vues.Composants.SearchBack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

@Composable
@Preview(showBackground = true)
fun EcranCategoriepreview()
{
    val application = Application()
    val scope = rememberCoroutineScope()
    val viewModel = LoginViewModel(application, scope)

    val listbk = mutableListOf(
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),
        Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","",
            BookCategory("",0),5,""),


    )

    val scaffoldState= rememberScaffoldState()


    val pd : PaddingValues= PaddingValues()
    val na = rememberNavController()


EcranCategorie( viewModel,na,"DROIT PUBLIC",pd,scaffoldState,scope)
}

@Composable
fun EcranCategorie( viewModel: LoginViewModel,
                    navController: NavController,
                    customParam : String,
                    pd:PaddingValues,
                    scaffoldState: ScaffoldState,
                    scope : CoroutineScope)
{
    LaunchedEffect(key1 = Unit)
    {
        scope.async {
            viewModel.ChargeLivres()
            viewModel.ChargeCategories()

        }
    }

    fun navigate()
    {
        navController.popBackStack()
    }

    var myparam = customParam
    var destination = "ecrancategorie/$myparam!!"
    viewModel.DetailCatego(myparam)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Column(modifier = Modifier.size(screenWidth, screenHeight)) {
        SearchBack(scaffoldState,scope,navController, ::navigate, destination, viewModel)
        AccueilName(viewModel._Ncat.value/*, viewModel::onCickCharge*/)
        ListBook(viewModel.Bglist.value, navController,customParam)
    }
}
