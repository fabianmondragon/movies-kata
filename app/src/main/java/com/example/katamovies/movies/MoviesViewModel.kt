package com.example.katamovies.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import com.example.domain.movies.usecase.MoviesUseCase
import com.example.katamovies.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    @IoDispatcher private val testDispatcher: CoroutineDispatcher

) : ViewModel() {

    private val _listOfMoviesState: MutableStateFlow<List<MovieD>> =
        MutableStateFlow(listOf())
    val listOfMoviesState = _listOfMoviesState.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
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