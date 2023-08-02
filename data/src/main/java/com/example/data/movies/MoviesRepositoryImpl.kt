package com.example.data.movies

import com.example.data.movies.datasources.MoviesLocalDataSource
import com.example.data.movies.datasources.MoviesRemoteDataSource
import com.example.data.movies.response.Movie
import com.example.data.movies.response.MovieResponse
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import com.example.domain.register.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.io.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) : MoviesRepository {
    override suspend fun getMovies(): Flow<ResultMovies<List<MovieD>, Exception>> {
        return moviesRemoteDataSource.getMovies().map { resultMovies ->
            when (resultMovies) {
                is ResultMovies.Success -> {
                    return@map ResultMovies.Success(
                        MapperMovies
                            .convertToListMovieDataToListMovieDomain(resultMovies.value)
                    )
                }
                is ResultMovies.Error -> {
                    return@map ResultMovies.Error(error = resultMovies.error)
                }
            }
        }
        // todo save information in local data base
    }
}