package com.example.katamovies.sigin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.katamovies.sigin.models.SigInUi
import com.example.katamovies.sigin.screens.ShowMessage
import com.example.katamovies.sigin.screens.SigInScreen
import com.example.katamovies.utils.Route

@Composable
fun SigInFragment(
    navController: NavController,
    sigInViewModel: SigInViewModel = hiltViewModel(),
    messageFromSigUp: String?
) {
    val resultToShowMessage by sigInViewModel.sigInUi.collectAsState(initial = SigInUi())

    validateShowMessageFromSigUp(sigInViewModel, messageFromSigUp, resultToShowMessage)
    SigInScreen(
        navController = navController,
        sigInViewModel = sigInViewModel,
        resultToShowMessage
    )
    gotToMovies(navController, resultToShowMessage.goToMovies)
}

@Composable
private fun validateShowMessageFromSigUp(
    sigInViewModel: SigInViewModel,
    messageFromSigUp: String?,
    sigInUi: SigInUi
) {
    if (sigInUi.showMessageFromSigUp)
        messageFromSigUp?.let {
            ShowMessage(messageFromSigUp!!)
        }

}

fun tryToSigIn(
    navController: NavController,
    viewModel: SigInViewModel,
    email: String = "",
    password: String = ""
) {
    if (viewModel.isEmailValid() && viewModel.isPasswordValid()){
        viewModel.sigIn(email, password)
    }

}

fun goToSigUp(navController: NavController) {
    navController.navigate(Route.SigUp.route)
}

@Composable
fun gotToMovies(navController: NavController, goToMovies: Boolean) {
    if (goToMovies) {
        LaunchedEffect(Unit) {
            navController.navigate(Route.List.route) {
                popUpTo(Route.List.route) { inclusive = true }
            }
        }
    }
}

