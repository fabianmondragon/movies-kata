package com.example.domain.movies.usecase

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import com.example.domain.movies.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun getMovies(): Flow<ResultMovies<List<MovieD>, Exception>> {
        return moviesRepository.getMovies()
    }
}