package com.kirt.finalbibliouut.Vues.Composants

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.Databases.BookCategory
import com.kirt.finalbibliouut.Databases.Bookdata
import com.kirt.finalbibliouut.R

@Composable
//@Preview(showBackground = true)
fun Bookpreview()
{
    var nv = rememberNavController()

}
@Composable
fun Book( ard : Bookdata, navController: NavController, dest:String?="")
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
            popUpTo("ecrancategorie/$dest!!")
        } },

        ) {

            Image(painter = image , contentDescription ="book", modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(5.dp)) )

        Spacer(modifier =Modifier.size(20.dp) )

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier = Modifier.height(70.dp)) {

            Text(text = ard.description, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

           // Text(text = "By raywenderlich Tutorial Team", fontSize = 12.sp, fontWeight = FontWeight.Normal)
            Spacer(modifier =Modifier.size(10.dp) )
            
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ListBookpreview()
{
 var listbkd = mutableListOf( Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","", BookCategory("",0),5,""), Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","", BookCategory("",0),5,""), Bookdata(1,"SYSCOA,Système Comptable, Ouest Africain Tableau de passage ","","", BookCategory("",0),5,""),
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
    var nv = rememberNavController()

    ListBook(lisbk = listbkd, nv,"")

}

@Composable
fun ListBook(lisbk : List<Bookdata>, navController: NavController, cust : String) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(lisbk) { index, book ->
            Book(ard = book, navController, cust)
        }
    }
}