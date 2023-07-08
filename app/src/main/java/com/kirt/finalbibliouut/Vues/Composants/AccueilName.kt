package com.kirt.finalbibliouut.Vues.Composants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun AccueilNamepreview()
{
    AccueilName("Listes des catégories")
}
@Composable
fun AccueilName(name : String/*, allBook : ()->Unit*/)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Row(horizontalArrangement = Arrangement.Center, modifier= Modifier.width(screenWidth)){
        Button(onClick = { /*allBook()*/ }, shape = RoundedCornerShape(50.dp), colors = ButtonDefaults.buttonColors(
            Color.DarkGray)) {
            Text(text = name, fontWeight = FontWeight.SemiBold, color = Color.White)
        }
    }
}
