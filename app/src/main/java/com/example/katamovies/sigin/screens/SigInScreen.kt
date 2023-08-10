package com.example.katamovies.sigin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.katamovies.R
import com.example.katamovies.sigin.SigInViewModel
import com.example.katamovies.sigin.goToSigUp
import com.example.katamovies.sigin.models.SigInUi
import com.example.katamovies.sigin.tryToSigIn
import com.example.katamovies.ui.theme.CustomYellow
import com.example.katamovies.ui.theme.GrayLetter
import com.example.moviesuikit.Text.CustomText
import com.example.moviesuikit.Text.CustomTextField
import com.example.moviesuikit.buttons.CustomButton.Companion.CustomButtonFull
import com.example.moviesuikit.dialog.CustomDialog
import com.example.moviesuikit.images.CustomCircularImage
import com.example.moviesuikit.loader.CustomLoader
import com.example.moviesuikit.spacer.CustomSpacer
import java.io.StringReader

@Composable
fun SigInScreen(
    navController: NavController, sigInViewModel: SigInViewModel, sigInUi: SigInUi
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
                contentDescription = stringResource(id = R.string.icon_description), // Add a description for accessibility
            )

            CustomSpacer.mediumSpacer()

            CustomTextField.TextFieldWithError(
                value = sigInViewModel.emailState.value,
                onValueChange = {
                    sigInViewModel.emailState.value = it
                    sigInViewModel.emailErrorState.value = false
                },
                isError = sigInViewModel.emailErrorState.value,
                placeholder = stringResource(id = R.string.email)
            )

            if (sigInViewModel.emailErrorState.value) {
                Text(
                    text = stringResource(id = R.string.valid_email),
                    color = Color.Red,
                    style = TextStyle(
                        fontSize = 12.sp, // Adjust the font size here
                        fontWeight = FontWeight.Light, color = Color.Red
                    ),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .align(Alignment.End)
                )
            }

            CustomSpacer.lowSpacer()

            CustomTextField.CustomTextFieldPassword(
                value = sigInViewModel.passwordState.value,
                onValueChange = {
                    sigInViewModel.passwordState.value = it
                    sigInViewModel.passwordErrorState.value = false
                },

                isError = sigInViewModel.passwordErrorState.value,
                placeholder = stringResource(id = R.string.password)
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
                        fontWeight = FontWeight.Light, color = Color.Red
                    )
                )
            }

            CustomSpacer.highSpacer()

            CustomButtonFull(text = stringResource(id = R.string.accept), onclick = {
                tryToSigIn(
                    navController,
                    sigInViewModel,
                    email = sigInViewModel.emailState.value,
                    password = sigInViewModel.passwordState.value
                )
            })

            CustomSpacer.highSpacer()

            Text(
                text = stringResource(id = R.string.enter_options), color = GrayLetter
            )
            CustomSpacer.highSpacer()
            Row {
                CustomCircularImage.CircularImage(imageResource = R.drawable.apple_black_logo)
                CustomCircularImage.CircularImage(imageResource = R.drawable.facebook_icon)
                CustomCircularImage.CircularImage(imageResource = R.drawable.google__g__logo)
            }
            CustomSpacer.highSpacer()

            CustomText.CustomTextWithClickablePart(
                onClick = {
                    goToSigUp(navController)
                }, firstPartWithoutBold = stringResource(id = R.string.no_accounts),
                secondPartWithBold = stringResource(id = R.string.sigup)
            )
        }
        CustomLoader.CircularProgressBarWithShape(sigInUi.showIsLoading)
        ShowMessage(sigInUi.messageToShow)
    }
}

@Composable
fun ShowMessage(message: String) {
    CustomDialog.CustomAlertDialog(
        message = message,
        title = stringResource(id = R.string.information),
        okButton = stringResource(id = R.string.ok),
        cancelButton = stringResource(id = R.string.cancel)
    )
}