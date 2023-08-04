package com.example.katamovies.movies.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.katamovies.movies.MoviesViewModel

@Composable
fun MoviesFragment (
    navController: NavController,
    moviesViewModel: MoviesViewModel = hiltViewModel()
){
    moviesViewModel.getMovies()
    val listOfMovies by moviesViewModel.listOfMoviesState.collectAsState(initial = emptyList())
    ScreenMovies(listOfMovies)
}

