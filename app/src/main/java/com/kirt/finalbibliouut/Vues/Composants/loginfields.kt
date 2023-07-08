package com.kirt.finalbibliouut.Vues.Composants
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private fun Context.doLogin() {
    Toast.makeText(
        this,
        "Something went wrong, try again later!",
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
@Preview(showBackground = true)
fun TextInputPreview()
{
    TextInput()
}

@Composable
fun TextInput()
{
    val passwordFocusRequester = FocusRequester()
    var value by remember { mutableStateOf("") }
    TextField(
        value = value,
        onValueChange = { value = it },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = { Icon(imageVector = Icons.Default.Person, null) },
        label = { Text(text = "Username") },
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        visualTransformation = VisualTransformation.None,
        keyboardActions = KeyboardActions(onNext = {
            passwordFocusRequester.requestFocus()
        })
    )
}
@Preview(showBackground = true)
@Composable
fun PasswordInputPreview()
{
    PasswordInput()
}
@Composable
fun PasswordInput()
{
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val passwordFocusRequester = FocusRequester()
    var value by remember { mutableStateOf("") }

    val focusRequester = passwordFocusRequester
    TextField(
        value = value,
        onValueChange = { value = it },
        modifier = Modifier
            .fillMaxWidth()
            .focusOrder(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, null) },
        label = { Text(text = "Password") },
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions =  KeyboardOptions(imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation(),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus()
            context.doLogin() })
    )

    Spacer(modifier = Modifier.size(20.dp))
    Button(onClick = {
        context.doLogin()
    }, modifier = Modifier.fillMaxWidth()) {
        Text("SIGN IN", Modifier.padding(vertical = 8.dp))
    }

}