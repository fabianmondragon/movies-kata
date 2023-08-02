package com.example.katamovies.signup

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.katamovies.signup.screens.SigInScreen
import com.example.katamovies.utils.Route
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun SigInFragment (
    navController: NavController,
    sigInViewModel: SigInViewModel = hiltViewModel()
){
    sigInViewModel.logIn()
    SigInScreen(navController)
}

fun tryToSigIn(navController: NavController){
    gotToMovies(navController)
}

fun gotToMovies(navController: NavController){
    navController.navigate(Route.List.route)
}
