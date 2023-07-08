package com.kirt.finalbibliouut.Vues.Composants

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kirt.finalbibliouut.Databases.Bookdata
import com.kirt.finalbibliouut.Databases.These
import com.kirt.finalbibliouut.R
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*@Preview(showBackground = true)
@Composable
fun previexThese()
{
val application = Application()
    val scope = rememberCoroutineScope()
    val viewModel = LoginViewModel(application, scope)
    val ard : These = These(1,"procuration ","http//fjj")
    val navController: NavController = rememberNavController()
    These(ard, scope, viewModel)

}*/
@Composable
fun These(ard : These, scope : CoroutineScope, viewModel: LoginViewModel)
{
    val image: Painter = painterResource(id = R.drawable.livre)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val url = ard.link
    val context = LocalContext.current

    var a = ard.id
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .clickable { }


        ) {

        Image(painter = image , contentDescription ="book", modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(5.dp)) )


            Text(text = ard.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                minLines = 3,
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis
                )

            // Text(text = "By raywenderlich Tutorial Team", fontSize = 12.sp, fontWeight = FontWeight.Normal)



           FloatingActionButton(shape = CircleShape,
               onClick = {

                   scope.launch {
                       viewModel.Telecharger(ard.id)

                       viewModel.ChargeTelech()
                       openBrowser(context,url)
                   }
               },
               modifier = Modifier.padding(8.dp)) {
               androidx.compose.material3.Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
           }
       }
    }



@Composable
@Preview(showBackground = true)
fun ListTheseprevi()
{
    val application = Application()
    val scope = rememberCoroutineScope()
    val viewModel = LoginViewModel(application, scope)
val lst = mutableListOf<These>(These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),

    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),

    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    These(1,"procuration ","http//fjj"),
    )
    ListThese(lst, scope, viewModel)
}
@Composable
fun ListThese(lisbk : MutableList<These>,scope : CoroutineScope, viewModel: LoginViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(lisbk) { index, these ->
            These(ard = these, scope = scope,viewModel)
        }
    }
}
private fun openBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(Intent.createChooser(intent, "Sélectionnez un navigateur"))
}