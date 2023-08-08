package com.example.katamovies.sigin

import androidx.compose.runtime.Composable
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
    sigInViewModel.logIn(messageFromSigUp)
    validateShowMessageFromSigUp(sigInViewModel, messageFromSigUp)
    SigInScreen(navController)
}

@Composable
private fun validateShowMessageFromSigUp(
    sigInViewModel: SigInViewModel,
    messageFromSigUp: String?
) {
    val sigInUi by sigInViewModel
        .sigInUi.collectAsState(initial = SigInUi())
    sigInViewModel.validateShowMessage(messageFromSigUp)
   if (sigInUi.showMessage)
        ShowMessage(messageFromSigUp!!)
}

fun tryToSigIn(navController: NavController) {
    gotToMovies(navController)
}

fun goToSigUp(navController: NavController) {
    navController.navigate(Route.SigUp.route)
}

fun gotToMovies(navController: NavController) {
    navController.navigate(Route.List.route)
}
