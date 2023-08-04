package com.example.data.movies.mappers

import com.example.data.movies.response.Movie
import com.example.data.utils.ConstantData
import com.example.domain.register.dtos.MovieD

class MapperMovies {

    companion object {
         private fun convertToMovieDataToMovieDomain(movie: Movie): MovieD {
            return MovieD(
                adult = movie.adult,
                backdropPath = movie.backdropPath,
                genreIds = movie.genreIds,
                id = movie.id,
                originalLanguage = movie.originalLanguage,
                originalTitle = movie.originalTitle,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = ConstantData.BASE_URL_IMAGE+movie.posterPath,
                releaseDate = movie.releaseDate,
                title = movie.title,
                video = movie.video,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount
            )
        }

        fun convertToListMovieDataToListMovieDomain(listMovie: List<Movie>): List<MovieD> {
            val list: MutableList<MovieD> = mutableListOf()
            listMovie.forEach {
                list.add(convertToMovieDataToMovieDomain(it))
            }
            return list
        }
    }

}