package com.example.katamovies.movies

import androidx.lifecycle.ViewModel
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import com.example.domain.register.usescases.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _listOfMoviesState: MutableStateFlow<List<MovieD>> =
        MutableStateFlow(listOf())
    val listOfMoviesState = _listOfMoviesState.asStateFlow()


    fun getMovies() {
        coroutineScope.launch {
            moviesUseCase.getMovies().collect { resultMovies ->
                when (resultMovies) {
                    is ResultMovies.Success -> {
                        _listOfMoviesState.value = resultMovies.value
                    }
                    else -> {

                    }
                }
            }
        }
    }
}