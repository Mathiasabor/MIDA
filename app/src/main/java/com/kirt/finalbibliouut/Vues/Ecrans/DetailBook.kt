package com.kirt.finalbibliouut.Vues.Ecrans

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.Databases.BookCategory
import com.kirt.finalbibliouut.Databases.Bookdata
import com.kirt.finalbibliouut.R
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import com.kirt.finalbibliouut.Vues.Composants.BackBar
import com.kirt.finalbibliouut.Vues.Composants.SearchAccueil
import com.kirt.finalbibliouut.Vues.Composants.SearchBack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
@Preview(showBackground = true)
fun DetailBookpreview()
{
    val scaffoldState= rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val pd : PaddingValues= PaddingValues()
    val application = Application()
    var vm = LoginViewModel(application, scope)
    val a = Bookdata(1,"",
        "","", BookCategory("",0),0,"45AD")
    DetailBook(vm,a.isbn,/*pd,*/scaffoldState,scope,navController)

}

@Composable
fun DetailBook(viewModel: LoginViewModel,
               iden : String,
               //pd: PaddingValues,
               scaffoldState: ScaffoldState,
               scope :CoroutineScope,
               navController: NavController,
               modifier: Modifier = Modifier
               )
{

    LaunchedEffect(key1 = Unit)
    {
        scope.async {
            viewModel.videConsultat()

        }
    }
    fun navigate()
    {
        navController.popBackStack()
    }
    viewModel.ReturnLivre(iden)
    var destination ="detailbook/$iden"
    val ccolor = Color(136,0,27)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var snackbar = scaffoldState.snackbarHostState

    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(screenHeight)
            .verticalScroll(rememberScrollState())) {

        SearchBack(scaffoldState,scope, navController ,::navigate, destination, viewModel)
    Image(
        painter = painterResource(id = R.drawable.livre ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(250.dp)
            .padding(30.dp, 30.dp, 30.dp, 20.dp)
            .clip(RoundedCornerShape(8.dp))
    )

        Text(text =  viewModel.book.value.description,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 26.sp,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
            )
        Divider(color = ccolor, thickness = 8.dp, modifier =  Modifier.padding(8.dp) )
        
        Text(text = viewModel.book.value.linodate,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)

            )

        Text(text = viewModel.book.value.editions,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            fontSize = 22.sp,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)

        )

        Divider(color = ccolor, thickness = 8.dp, modifier =  Modifier.padding(8.dp) )

       Row {
           Text(text = "Exemplaire",
               fontWeight = FontWeight.SemiBold,
               fontStyle = FontStyle.Normal,
               fontSize = 22.sp,
               fontFamily = FontFamily.Monospace,
               textAlign = TextAlign.Start,
               modifier = Modifier.padding(8.dp),
               textDecoration = TextDecoration.Underline

           )
           Text(text = ": ${viewModel.book.value.exemplaire}",
               fontWeight = FontWeight.SemiBold,
               fontStyle = FontStyle.Normal,
               fontSize = 22.sp,
               fontFamily = FontFamily.Monospace,

               modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 8.dp),


           )


       }

        ExtendedFloatingActionButton(
            backgroundColor = Color.Blue,
            contentColor = Color.White,
            text = {
                Text(text = "Consulter")
                   },
            onClick = {

                scope.launch {
                    viewModel.Consultat(viewModel.book.value.isbn)
                   viewModel.Consulter()
                        snackbar.showSnackbar("livre consulté")

                }

                      },
            icon = { Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "")}

        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = viewModel._isbn.value, fontSize = 14.sp)

}

}
