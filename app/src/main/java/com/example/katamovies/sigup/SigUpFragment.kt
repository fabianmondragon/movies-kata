package com.example.katamovies.sigup


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.katamovies.sigin.models.ResultSigUpUi
import com.example.katamovies.sigup.screens.ShowMessage
import com.example.katamovies.sigup.screens.SigUpScreen
import com.example.katamovies.utils.Route

@Composable
fun SigUpFragment(
    navController: NavController,
    sigUpViewModel: SigUpViewModel = hiltViewModel()
) {

    val resultSigUpUi by sigUpViewModel.resultListSigUpUi.collectAsState(initial = ResultSigUpUi())
    SigUpScreen(
        navController = navController,
        onSigUpUser = ::sigUpUser,
        viewModel = sigUpViewModel,
        isLoading = resultSigUpUi.showIsLoading
    )
    validaRespond(navController = navController, resultSigUpUi = resultSigUpUi)

}

@Composable
private fun validaRespond(resultSigUpUi: ResultSigUpUi, navController: NavController) {

    if (resultSigUpUi.answered) {
        if (resultSigUpUi.isSuccess) {
            val message = resultSigUpUi.messageToShow
            val destination = "${Route.SigIn.route}/$message"

            LaunchedEffect(Unit) {
                navController.navigate(destination) {
                    popUpTo(Route.SigIn.route) { inclusive = true }
                }
            }
        } else {
            ShowMessage(message = resultSigUpUi.messageToShow)
        }
    }


}

private fun sigUpUser(
    userName: String,
    email: String,
    password: String,
    sigUpViewModel: SigUpViewModel
) {

    sigUpViewModel.sigUpUser(userName, email, password)

}