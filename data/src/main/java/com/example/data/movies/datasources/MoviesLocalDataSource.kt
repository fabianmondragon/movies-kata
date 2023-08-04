package com.example.data.movies.datasources

import com.example.data.database.entities.MovieEntity
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    suspend fun getMovies(): Flow<ResultMovies<List<MovieD>, Exception>>
    suspend fun saveMovies(movies: MovieEntity )
}