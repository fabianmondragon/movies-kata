package com.example.katamovies.sigin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.katamovies.R
import com.example.katamovies.sigin.SigInViewModel
import com.example.katamovies.sigin.goToSigUp
import com.example.katamovies.sigin.models.SigInUi
import com.example.katamovies.sigin.tryToSigIn
import com.example.katamovies.ui.theme.CustomYellow
import com.example.katamovies.ui.theme.Gray
import com.example.katamovies.ui.theme.GrayLetter
import java.io.StringReader

@Composable
fun SigInScreen(
    navController: NavController,
    sigInViewModel: SigInViewModel,
    sigInUi: SigInUi
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomYellow)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.imdb_logo), // Replace with your icon resource
                contentDescription = stringResource(id = R.string.icon_description ), // Add a description for accessibility
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = sigInViewModel.emailState.value,
                onValueChange = {
                    sigInViewModel.emailState.value = it
                    sigInViewModel.emailErrorState.value = false
                },
                isError = sigInViewModel.emailErrorState.value,
                placeholder = { Text(stringResource(id =R.string.email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
            )
            if (sigInViewModel.emailErrorState.value) {
                Text(
                    text = stringResource(id = R.string.valid_email),
                    color = Color.Red,
                    style = TextStyle(
                        fontSize = 12.sp, // Adjust the font size here
                        fontWeight = FontWeight.Light,
                        color = Color.Red
                    ),
                    modifier = Modifier
                        .padding(end = 10.dp)
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
                placeholder = { Text(stringResource(id = R.string.password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, MaterialTheme.shapes.medium)
            )
            if (sigInViewModel.passwordErrorState.value) {
                Text(
                    text = stringResource(id = R.string.password_emtpy),
                    color = Color.Red,
                    modifier = Modifier
                        .padding(end = 10.dp)
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
                colors = ButtonDefaults.buttonColors(Gray)

            ) {
                Text(stringResource(id = R.string.accept))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text =stringResource(id = R.string.enter_options),
                color = GrayLetter )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                CircularImage(imageResource = R.drawable.apple_black_logo)
                CircularImage(imageResource = R.drawable.facebook_icon)
                CircularImage(imageResource = R.drawable.google__g__logo)
            }
            Spacer(modifier = Modifier.height(16.dp))



            Spacer(modifier = Modifier.height(10.dp))
            CustomTextWithClickablePart(
                onClick = {
                    goToSigUp(navController)
                }
            )
        }
        CircularProgressBarWithShape(sigInUi.showIsLoading)
        ShowMessage(sigInUi.messageToShow)
    }
}

@Composable
fun CircularImage(imageResource: Int) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(20.dp)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun CustomTextWithClickablePart(onClick: () -> Unit) {
    val annotatedString = buildAnnotatedString() {
        append(stringResource(id = R.string.no_accounts))
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append(stringResource(id = R.string.sigup))
        pop()
    }

    ClickableText(
        text = annotatedString,
        onClick = {
            onClick.invoke()
        }
    )
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
                    Text(text = stringResource(id = R.string.information))
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
}