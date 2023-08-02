package com.example.data.movies.service

import com.example.data.movies.response.MovieResponse
import com.example.data.register.utils.ConstantData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apikey: String = ConstantData.API_KEY
    ): Response<MovieResponse>
}