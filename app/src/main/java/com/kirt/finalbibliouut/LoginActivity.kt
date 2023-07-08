package com.kirt.finalbibliouut

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import com.kirt.finalbibliouut.ui.theme.FinalBiblioUUTTheme
import compose.material.theme.LoginPage

import kotlinx.coroutines.CoroutineScope

class LoginActivity : ComponentActivity() {



    private lateinit var sharedPreferences: SharedPreferences

    lateinit var context :  Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //GlobalScope.launch {
        context = applicationContext


        sharedPreferences = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)






        if (isLoggedIn) {
            navigateToAccueil()
        }
        setContent {

            val scaffoldState: ScaffoldState = rememberScaffoldState()
            val scope: CoroutineScope = rememberCoroutineScope()
            val viewModel = LoginViewModel(context, scope)
            val navController = rememberNavController()
            FinalBiblioUUTTheme {
                Scaffold(
                    scaffoldState = scaffoldState,

                    ) { pd ->
                    NavHost(
                        navController = navController,
                        startDestination = "login_page",
                        builder = {
                            composable(
                                "login_page",
                                content = {
                                    LoginPage(
                                        navController = navController,
                                        pd,
                                        scaffoldState,
                                        scope,
                                        ::onLogin,
                                        viewModel
                                    )
                                })


                        })
                }
            }

            // A surface container using the 'background' color from the theme
            // if (isLoggedIn) {
            //      navigateToAccueil()
            //   }
            //   Login(viewModel,::onLogin)

        }

   // }

}




     fun onLogin()
    {
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
        navigateToAccueil()
    }

    private fun navigateToAccueil()
    {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }





}



@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FinalBiblioUUTTheme {
        Greeting2("Android")
    }
}