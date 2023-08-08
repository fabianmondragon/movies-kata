package com.example.katamovies.sigin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.katamovies.sigin.goToSigUp
import com.example.katamovies.sigin.tryToSigIn
import kotlinx.coroutines.delay

@Composable
fun SigInScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        BuildTextField()
        BuildTextField()
        BuildButton(navController)
        BuildTextButton(navController)
    }
}

@Composable
fun BuildButton(navController: NavController) {
    Button(onClick = {
        tryToSigIn(navController)

    }) {
        Text(text = "Aceptar")
    }
}

@Composable
fun BuildTextButton(navController: NavController) {
    TextButton(
        onClick = { goToSigUp(navController = navController) },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Register")
    }
}

@Composable
fun BuildTextField() {
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

@Composable
fun ShowMessage(message: String) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Information")
            },
            text = {
                Text(text = message)
            },
            confirmButton = {
                Button(onClick = {
                    showDialog = false

                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}