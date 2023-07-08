package com.kirt.finalbibliouut.Vues.Composants

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kirt.finalbibliouut.Databases.Bookdata
import com.kirt.finalbibliouut.R
import com.kirt.finalbibliouut.Routes.Ecrans

@Composable
fun BookRecherche(ard : Bookdata, navController: NavController)
{
    val image: Painter = painterResource(id = R.drawable.livre)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var a = ard.isbn
    Row( modifier = Modifier
        .width(screenWidth)
        .height(70.dp)
        .clickable { navController.navigate("detailbook/$a"){
            popUpTo(Ecrans.SearchPage.destination)
        } },

        ) {

        Image(painter = image , contentDescription ="book", modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(5.dp)) )

        Spacer(modifier = Modifier.size(20.dp) )

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier = Modifier.height(70.dp)) {

            Text(text = ard.description, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

            // Text(text = "By raywenderlich Tutorial Team", fontSize = 12.sp, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.size(10.dp) )

        }
    }
}
@Composable
fun ListBookRecherche(lisbk : MutableList<Bookdata>, navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(lisbk) { index, book ->
            BookRecherche(ard = book, navController)
        }
    }
}