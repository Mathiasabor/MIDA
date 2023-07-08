package com.kirt.finalbibliouut.Vues.Composants

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kirt.finalbibliouut.Databases.These
import com.kirt.finalbibliouut.R
import com.kirt.finalbibliouut.ViewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TheseRecherche(ard : These, scope : CoroutineScope, viewModel: LoginViewModel)
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





        FloatingActionButton(shape = CircleShape,
            onClick = {

                scope.launch {
                    viewModel.Telecharger(ard.id)
                    openBrowser(context,url)
                }
            },
            modifier = Modifier.padding(8.dp)) {
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
        }
    }
}
@Composable
fun ListTheseRecherche(lisbk : MutableList<These>,scope : CoroutineScope, viewModel: LoginViewModel) {
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