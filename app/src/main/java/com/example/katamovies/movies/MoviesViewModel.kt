package com.example.katamovies.movies

import androidx.lifecycle.ViewModel
import com.example.domain.register.ResultMovies
import com.example.domain.register.usescases.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
):ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getMovies (){
        coroutineScope.launch {
            moviesUseCase.getMovies().collect { resultMovies ->
                when (resultMovies){
                    is ResultMovies.Success -> {
                        resultMovies.value
                    }
                    else -> {

                    }
                }
            }
        }
    }
}