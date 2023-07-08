package com.kirt.finalbibliouut.Vues.Composants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.Routes.Ecrans

@Composable
@Preview(showBackground = true)
fun TheseNamepreview()
{
    val destination =""
    val nv = rememberNavController()
    TheseName("Listes des catégories", nv, destination)
}
@Composable
fun TheseName(name : String, navController: NavController, destination : String)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier= Modifier
            .width(screenWidth)
            .padding(8.dp, 0.dp, 8.dp, 0.dp)
    ){
        Button(onClick = { /*allBook()*/ }, shape = RoundedCornerShape(50.dp), colors = ButtonDefaults.buttonColors(
            Color.Transparent)) {
            Text(text = name, fontWeight = FontWeight.SemiBold)
        }
        Button(onClick = { navController.navigate(Ecrans.SearchThese.destination){
            popUpTo(destination)
        } }, shape = RoundedCornerShape(50.dp), colors = ButtonDefaults.buttonColors(
            Color.Blue)) {
            Row() {

                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        tint = Color.White
                    )

                Text(text = "Rechercher", fontWeight = FontWeight.SemiBold, color = Color.White)
            }

        }
    }
}