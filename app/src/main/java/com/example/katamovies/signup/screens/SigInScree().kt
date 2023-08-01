package com.example.katamovies.signup.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.katamovies.signup.tryToSigIn
import com.example.katamovies.utils.Route

@Composable
fun SigInScreen(navController: NavController) {
    Column (modifier = Modifier.fillMaxWidth()){
        BuildTextField()
        BuildTextField()
        BuildButton(navController)
        
    }
}

@Composable
fun BuildButton (navController: NavController) {
    Button(onClick = {
        tryToSigIn(navController)

    }) {
        Text(text = "Aceptar")
    }
}

@Composable
fun BuildTextField (){
    return BasicTextField(
        value = "hola",
        onValueChange = {
            
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
               
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
    )
}
