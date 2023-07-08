package com.kirt.finalbibliouut

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kirt.finalbibliouut.Databases.BookCategory
import com.kirt.finalbibliouut.Databases.Bookdata
import com.kirt.finalbibliouut.Preferences.UsersSharedPreferencesRepository
import com.kirt.finalbibliouut.Preferences.keySharedPreferencesRepository
import com.kirt.finalbibliouut.Routes.Ecrans
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import com.kirt.finalbibliouut.Vues.Composants.MenuIdenBox
import com.kirt.finalbibliouut.Vues.Ecrans.Accueil
import com.kirt.finalbibliouut.Vues.Ecrans.Consultes
import com.kirt.finalbibliouut.Vues.Ecrans.DetailBook
import com.kirt.finalbibliouut.Vues.Ecrans.EcranCategorie
import com.kirt.finalbibliouut.Vues.Ecrans.Login
import com.kirt.finalbibliouut.Vues.Ecrans.SearchPage
import com.kirt.finalbibliouut.Vues.Ecrans.SearchThesePage
import com.kirt.finalbibliouut.Vues.Ecrans.Telecharge
import com.kirt.finalbibliouut.Vues.Ecrans.Theses
import com.kirt.finalbibliouut.ui.theme.FinalBiblioUUTTheme
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences


    var ecatVar : String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

        setContent {




            val scaffoldState: ScaffoldState = rememberScaffoldState()
            val scope: CoroutineScope = rememberCoroutineScope()
            val viewModel = LoginViewModel(application, scope )
            val navController = rememberNavController()
            FinalBiblioUUTTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerContent = {
                        MenuIdenBox(
                            viewModel,
                            navController,
                            ::onLogout,
                            scaffoldState,
                            scope
                        )
                    },

                    ) { pd ->
                    NavHost(
                        navController = navController,
                        startDestination = Ecrans.Accueil.destination
                    )
                    {
                        composable(Ecrans.Accueil.destination)
                        {
                            Accueil(viewModel = viewModel, navController, pd, scaffoldState, scope)
                        }



                        composable(
                            "ecrancategorie/{customParam}",
                            arguments = listOf(navArgument("customParam") {
                                type = NavType.StringType
                            })
                        )
                        { backStackEntry ->
                            val customParam = backStackEntry.arguments?.getString("customParam")
                            ecatVar = customParam!!
                            EcranCategorie(
                                viewModel,
                                navController,
                                customParam!!,
                                pd,
                                scaffoldState,
                                scope
                            )
                        }

                        composable(
                            "detailbook/{customParam}",
                            arguments = listOf(navArgument("customParam") {
                                type = NavType.StringType
                            })
                        )
                        { backStackEntry ->
                            val customParam = backStackEntry.arguments?.getString("customParam")
                            DetailBook(
                                viewModel,
                                customParam!!,/*pd,*/
                                scaffoldState,
                                scope,
                                navController
                            )
                        }

                        composable(Ecrans.Telecharge.destination)
                        {
                            Telecharge(viewModel, navController, scaffoldState, scope)
                        }

                        composable(Ecrans.SearchPage.destination)
                        {
                            SearchPage(viewModel = viewModel, navController = navController)
                        }

                        composable(Ecrans.Consultes.destination)
                        {
                            Consultes(viewModel, navController, scaffoldState, scope)
                        }

                        composable(Ecrans.These.destination)
                        {
                            Theses(viewModel, navController, pd, scaffoldState, scope)
                        }

                        composable(Ecrans.SearchThese.destination)
                        {
                            SearchThesePage(viewModel, navController, scope)
                        }

                    }


                }


            }


        }
    }
private fun onLogout()
{
    sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
    videInfoUser()
    videToken()
    navigateToLogin()
}


    private fun navigateToLogin()
    {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun videToken()
    {
        val TokenShared = getSharedPreferences("Token",Context.MODE_PRIVATE)
        val keySharedPrefs = keySharedPreferencesRepository(TokenShared)
        keySharedPrefs.Videwrite()

    }

    fun videInfoUser()
    {
        val UserSharedPrefs = getSharedPreferences("User", Context.MODE_PRIVATE)
        val UserSharedPrefsRepo = UsersSharedPreferencesRepository(UserSharedPrefs)
        UserSharedPrefsRepo.Videwrite()
    }

    fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(Intent.createChooser(intent, "Sélectionnez un navigateur"))
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FinalBiblioUUTTheme {
        Greeting("Android")
    }
}