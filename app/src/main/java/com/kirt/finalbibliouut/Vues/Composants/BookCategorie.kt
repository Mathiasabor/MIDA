package com.kirt.finalbibliouut.Vues.Composants

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.luminance
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.Databases.BookCategory
import com.kirt.finalbibliouut.R
import com.kirt.finalbibliouut.Routes.Ecrans
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
//@Preview(showBackground = true)
fun BookCategoriepreview()
{
    val na = rememberNavController()
    var bc = BookCategory("droit des affaires",0)
    val application = Application().applicationContext
    val scope = rememberCoroutineScope()
    var viewModel = LoginViewModel(application, scope)
BookCategorie(bc, viewModel, na)
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun BookCategorie(Categ :BookCategory, viewModel: LoginViewModel, navController: NavController) {




    var categno = Categ.nom

    var colorobj = generateRandomColors()

      Card( backgroundColor = colorobj.first,

          modifier = Modifier
              .padding(8.dp)
              //.size(120.dp)
              .background(Color.Transparent)
              .clip(RoundedCornerShape(16.dp))
              .clickable {


                  //viewModel.DetailCatego(Categ)
                  navController.navigate("ecrancategorie/$categno")
                  {
                      popUpTo(Ecrans.Accueil.destination)


                  }

              },


      ) {

          Column(
              modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally
          ) {
              Image(
                  painter = painterResource(id = R.drawable.livre),
                  contentDescription = null,
                  modifier = Modifier.size(64.dp),

              )
              Text(
                  text = Categ.nom,
                  color = colorobj.second,
                  //modifier = Modifier.padding(start = 3.dp),
                  modifier = Modifier.fillMaxSize().padding(start = 3.dp),
                  //style = MaterialTheme.typography.subtitle1
              )
          }

      }



    }


@Composable
@Preview(showBackground = true)
fun listBookCategoriepreview()
{

    val na = rememberNavController()
    val application = Application()
    val scope = rememberCoroutineScope()
    var vm = LoginViewModel(application, scope)

    val catlist = mutableListOf(BookCategory("DROIT PUBLIC", 1),

        BookCategory("DROIT PUBLIC", 1),
        BookCategory("DROIT COMMERCIAL", 1),
        BookCategory("DROIT PRIVEE", 1),
        BookCategory("PHYSIQUE", 1),
        BookCategory("ADMINISTRATION RESEAU", 1),
        BookCategory("PROGRAMMATION", 1),
        BookCategory("COMPTABILITE", 1),
        BookCategory("GESTION DE PROJET", 1),
        BookCategory("ECONOMIE", 1),
        BookCategory("DROIT PUBLIC", 1),
        BookCategory("DROIT PUBLIC", 1),
        BookCategory("DROIT PUBLIC", 1),
        BookCategory("DROIT PUBLIC", 1),


        )
listBookCategorie(cat = catlist, vm, na)

}

@Composable
fun listBookCategorie(cat :List<BookCategory>,viewModel: LoginViewModel, navController: NavController)
{

    LazyVerticalGrid(columns = GridCells.Fixed(3),


        )
    {
            items(cat){ cate ->
                BookCategorie(cate,viewModel, navController)

}
    }


}

fun generateRandomColors(): Pair<Color, Color> {
    val randomBackground = Color(
        red = (0..255).random() / 255f,
        green = (0..255).random() / 255f,
        blue = (0..255).random() / 255f
    )

    val randomText = if (randomBackground.luminance() > 0.5f) {
        Color.Black
    } else {
        Color.White
    }

    return Pair(randomBackground, randomText)
}

/*
fun generateRandomColors(): Triple<Color, Color, Color> {
    val randomBackground = generateRandomColor()

    val textContrastColor = if (randomBackground.luminance() > 0.5f) {
        Color.Black
    } else {
        Color.White
    }

    val imageContrastColor = generateContrastColor(randomBackground)

    return Triple(randomBackground, textContrastColor, imageContrastColor)
}

private fun generateRandomColor(): Color {
    return Color(
        red = (0..255).random() / 255f,
        green = (0..255).random() / 255f,
        blue = (0..255).random() / 255f
    )
}

private fun generateContrastColor(background: Color): Color {
    val luminanceThreshold = 0.5f
    val backgroundLuminance = background.luminance()

    val contrastRed = if (backgroundLuminance > luminanceThreshold) 0f else 1f
    val contrastGreen = if (backgroundLuminance > luminanceThreshold) 1f else 0f
    val contrastBlue = if (backgroundLuminance > luminanceThreshold) 1f else 0f

    return Color(contrastRed, contrastGreen, contrastBlue)
}

*/
