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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
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
import com.kirt.finalbibliouut.Databases.Consulte
import com.kirt.finalbibliouut.Databases.Telecharge
import com.kirt.finalbibliouut.Databases.These
import com.kirt.finalbibliouut.MainActivity
import com.kirt.finalbibliouut.R
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@Preview(showBackground = true)
fun Telechprev()
{
    val application = Application()
    val ard : These = These(1,"procuration ","http//fjj")
    val navController: NavController = rememberNavController()
    var dates : String ="2023-05-17"
    val scope = rememberCoroutineScope()
    val viewModel = LoginViewModel(application, scope)
    Telech(ard, dates, scope, viewModel )
}


@Composable
fun Telech(ard : These, dates : String, scope : CoroutineScope, viewModel: LoginViewModel)
{
    val image: Painter = painterResource(id = R.drawable.livre)
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val url = ard.link
    val context = LocalContext.current



    var a = ard.id
    Row( modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .clickable { }


    ) {

        Image(painter = image , contentDescription ="book", modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(5.dp)) )



        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f),) {

            Text(text = ard.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis)

            Text(text = dates, fontSize = 12.sp, fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis)


        }



            FloatingActionButton(shape = CircleShape,
                onClick = {
                    scope.launch {
                        viewModel.Telecharger(ard.id)
                        viewModel.ChargeTelech()
                        openBrowser(context,url)
                    }

                },
                modifier = Modifier.padding(8.dp)) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
            }

    }
}

@Composable
@Preview(showBackground = true)
fun ListTelechprev()
{

}

@Composable
fun ListTelech(lisbk : MutableList<Telecharge>, viewModel: LoginViewModel,  scope : CoroutineScope)
{

    var theseg = These(1,"google","http://www.google.com")
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(lisbk) { index, tels ->
            viewModel._LisThese.value.forEach { tel->

                if(tel.id == tels.thesed)
                {
                    theseg = tel
                }


            }
            Telech(theseg, tels.dates, scope, viewModel)


        }

}

}

private fun openBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(Intent.createChooser(intent, "Sélectionnez un navigateur"))
}