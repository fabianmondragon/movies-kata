package com.example.katamovies.sigin.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.katamovies.sigin.SigInViewModel
import com.example.katamovies.sigin.models.SigInUi
import com.example.katamovies.sigin.tryToSigIn


@Composable
fun SigInScreen(
    navController: NavController,
    sigInViewModel: SigInViewModel,
    sigInUi: SigInUi
) {
    //var password by remember { mutableStateOf("") }
    //var email by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = sigInViewModel.emailState.value,
                onValueChange = {
                    sigInViewModel.emailState.value = it
                    sigInViewModel.emailErrorState.value = false
                },
                isError = sigInViewModel.emailErrorState.value,
                placeholder = { Text("email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
            )
            if (sigInViewModel.emailErrorState.value) {
                Text(
                    text = "Please enter a valid email",
                    color = Color.Red,
                    style = TextStyle(
                        fontSize = 12.sp, // Adjust the font size here
                        fontWeight = FontWeight.Light,
                        color = Color.Red
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.End)
                )
            }

            Spacer(modifier = Modifier.height(2.dp))
            TextField(
                value = sigInViewModel.passwordState.value,
                onValueChange = {
                    sigInViewModel.passwordState.value = it
                    sigInViewModel.passwordErrorState.value = false
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                isError = sigInViewModel.passwordErrorState.value,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text("password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
            )
            if (sigInViewModel.passwordErrorState.value) {
                Text(
                    text = "Password cannot be empty",
                    color = Color.Red,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.End),
                    style = TextStyle(
                        fontSize = 12.sp, // Adjust the font size here
                        fontWeight = FontWeight.Light,
                        color = Color.Red
                    )
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    tryToSigIn(
                        navController, sigInViewModel,
                        email = sigInViewModel.emailState.value,
                        password = sigInViewModel.passwordState.value
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Aceptar")
            }
        }
        CircularProgressBarWithShape(sigInUi.showIsLoading)
        ShowMessage(sigInUi.messageToShow)
    }
}


@Composable
fun CircularProgressBarWithShape(isLoading: Boolean) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.5f))
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ShowMessage(message: String) {
    if (message != "") {
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
}