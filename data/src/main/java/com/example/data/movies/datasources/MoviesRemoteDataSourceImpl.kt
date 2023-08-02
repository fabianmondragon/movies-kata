package com.example.data.movies.datasources

import com.example.data.movies.response.Movie
import com.example.data.movies.service.MoviesService
import com.example.data.movies.response.MovieResponse
import com.example.domain.register.ResultMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService
) : MoviesRemoteDataSource {
    override suspend fun getMovies(): Flow<ResultMovies<List<Movie>, Exception>> {
        val response = moviesService.getTopRatedMovies()
        return if (response.isSuccessful) {
            flow {
                response.body()?.let { ResultMovies.Success(it.results) }?.let { emit(it) }
            }
        } else {
            flow {
                val error = IOException("Error ${response.code()}: ${response.message()}")
                emit (ResultMovies.Error(error))
            }
        }
    }
}