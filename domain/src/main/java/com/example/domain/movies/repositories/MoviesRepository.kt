package com.example.domain.movies.repositories

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovies(): Flow<ResultMovies<List<MovieD>, Exception>>
    suspend fun getRemoteMovies(): Flow<ResultMovies<List<MovieD>, Exception>>
}