package com.kirt.finalbibliouut.Vues.Composants

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import com.kirt.finalbibliouut.R

@Composable
@Preview(showBackground = true)
fun BlocAccueilpreview()
{
    BlocAccueil()
}


@Composable
fun BlocAccueil()
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Card( elevation = 8.dp,shape = RoundedCornerShape(10.dp), modifier = Modifier
        .size(screenWidth, 110.dp)
        .padding(10.dp)) {

        Image(
            painter = painterResource(id = R.drawable.bibliouutlogocopie ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

    }
}