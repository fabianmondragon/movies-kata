package com.example.katamovies.movies

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import com.example.domain.movies.usecase.MoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    lateinit var moviesUseCase: MoviesUseCase
    lateinit var moviesViewModel: MoviesViewModel
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        moviesUseCase = mockk()
        Dispatchers.setMain(testDispatcher)
        moviesViewModel = MoviesViewModel(moviesUseCase, testDispatcher)
    }

    @Test
    fun `test getMovies with success result`() = runTest(testDispatcher) {
        // Given
        val movieList: List<MovieD> = listOf(
            MovieD(
                adult = false,
                backdropPath = "/path_to_backdrop1.jpg",
                genreIds = listOf(12, 28, 80),
                id = 1,
                originalLanguage = "en",
                originalTitle = "Movie 1",
                overview = "Overview of Movie 1",
                popularity = 123.45,
                posterPath = "/path_to_poster1.jpg",
                releaseDate = "2023-07-01",
                title = "Movie 1",
                video = false,
                voteAverage = 7.8,
                voteCount = 1000
            ),
            MovieD(
                adult = false,
                backdropPath = "/path_to_backdrop2.jpg",
                genreIds = listOf(18, 36),
                id = 2,
                originalLanguage = "en",
                originalTitle = "Movie 2",
                overview = "Overview of Movie 2",
                popularity = 98.76,
                posterPath = "/path_to_poster2.jpg",
                releaseDate = "2023-07-15",
                title = "Movie 2",
                video = false,
                voteAverage = 6.5,
                voteCount = 750
            )
        )
        val result = ResultMovies.Success(movieList)
        coEvery { moviesUseCase.getMovies() } returns flowOf(result)

        // When
        moviesViewModel.getMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(moviesViewModel.listOfMoviesState.value, movieList)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

}