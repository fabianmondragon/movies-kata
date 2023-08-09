package com.example.katamovies.sigup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.katamovies.R
import com.example.katamovies.sigup.SigUpViewModel
import kotlin.reflect.KFunction4

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SigUpScreen(
    navController: NavController,
    onSigUpUser: KFunction4<String, String, String, SigUpViewModel, Unit>,
    viewModel: SigUpViewModel,
    isLoading: Boolean
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

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
            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(8.dp),
                text = stringResource(id = R.string.account_created),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text(stringResource(id = R.string.user_name)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.height(2.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(stringResource(id = R.string.email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(2.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text(stringResource(id = R.string.password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(8.dp),
                text = stringResource(id = R.string.password_caution),
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    onSigUpUser.invoke(username, email, password, viewModel)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(stringResource(id = R.string.accept))
            }
        }

        CircularProgressBarWithShape(isLoading)

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
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = stringResource(id = R.string.error))
            },
            text = {
                Text(text = message)
            },
            confirmButton = {
                Button(onClick = {
                    showDialog = false

                }) {
                    Text(stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        )
    }
}
