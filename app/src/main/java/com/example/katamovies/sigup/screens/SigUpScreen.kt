package com.example.katamovies.sigup.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.katamovies.R
import com.example.katamovies.sigup.SigUpViewModel
import com.example.moviesuikit.buttons.CustomButton
import com.example.moviesuikit.Text.CustomTextField
import com.example.moviesuikit.dialog.CustomDialog
import com.example.moviesuikit.loader.CustomLoader
import com.example.moviesuikit.spacer.CustomSpacer
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

            CustomSpacer.mediumSpacer()

            CustomTextField.NormalTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = stringResource(id = R.string.user_name),
            )

            CustomSpacer.lowSpacer()

            CustomTextField.NormalTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(id = R.string.email),
            )

            CustomSpacer.lowSpacer()

            CustomTextField.CustomTextFieldPassword(
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(id = R.string.password)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(8.dp),
                text = stringResource(id = R.string.password_caution),
                style = MaterialTheme.typography.labelSmall
            )

            CustomSpacer.highSpacer()

            CustomButton.CustomButtonFull(
                onclick = {
                    onSigUpUser.invoke(username, email, password, viewModel)
                },
                text = stringResource(id = R.string.accept)
            )
        }

        CustomLoader
            .CircularProgressBarWithShape(isLoading = isLoading)
    }
}

@Composable
fun ShowMessage(message: String) {
    CustomDialog.CustomAlertDialog(
        message = message,
        title = stringResource(id = R.string.error),
        okButton = stringResource(id = R.string.ok),
        cancelButton = stringResource(id = R.string.cancel)
    )
}
