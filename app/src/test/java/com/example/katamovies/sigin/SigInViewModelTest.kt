package com.example.katamovies.sigin


import com.example.domain.register.ResultMovies
import com.example.domain.register.sigin.usecases.SigIngUseCase
import io.mockk.coEvery
import io.mockk.coVerify

import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.*

import kotlinx.coroutines.flow.flowOf

import kotlinx.coroutines.test.*

import org.junit.Test

import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.time.ExperimentalTime


@ExperimentalTime
@ExperimentalCoroutinesApi
class SigInViewModelTest {

    private val sigIngUseCase: SigIngUseCase = mockk()
    private lateinit var viewModel: SigInViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        MockitoAnnotations.initMocks(this)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test signIn with success response`() = runTest {

        // Given
        val email = "valid@email.com"
        val password = "StrongP@ss1"
        val successResponse = ResultMovies.Success(true)
        viewModel = SigInViewModel(sigIngUseCase, testDispatcher = testDispatcher)

        coEvery { sigIngUseCase.sigIngUser(email, password) } returns flowOf(successResponse)

        // When
        viewModel.sigIn(email, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(false, viewModel.sigInUi.value?.showIsLoading)
        assertEquals(false, viewModel.sigInUi.value?.showMessageFromSigUp)
        assertEquals(false, viewModel.sigInUi.value?.goToMovies)
        coVerify(exactly = 1) {
            sigIngUseCase.sigIngUser(email, password)

        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test signIn with error response`() = runTest {

        // Given
        val email = "valid@email.com"
        val password = "StrongP@ss1"
        val messageToCompare = "Error"
        val failResponse = ResultMovies.Error(Exception("Error"))
        viewModel = SigInViewModel(sigIngUseCase, testDispatcher = testDispatcher)

        coEvery { sigIngUseCase.sigIngUser(email, password) } returns flowOf(failResponse)

        // When
        viewModel.sigIn(email, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(false, viewModel.sigInUi.value?.showIsLoading)
        assertEquals(messageToCompare, viewModel.sigInUi.value?.messageToShow)
        coVerify(exactly = 1) {
            sigIngUseCase.sigIngUser(email, password)
        }
    }

    @Test
    fun `test isEmailValid with valid email`() {
        // Given
        viewModel = SigInViewModel(sigIngUseCase, testDispatcher = testDispatcher)
        viewModel.emailState.value = "test@example.com"

        // When
        viewModel.isEmailValid()

        // Then
        assert(!viewModel.emailErrorState.value)
    }

    @Test
    fun `test isEmailValid with invalid email`() {
        // Given
        viewModel = SigInViewModel(sigIngUseCase, testDispatcher = testDispatcher)
        viewModel.emailState.value = "invalid-email"

        // When
        viewModel.isEmailValid()

        // Then
        assertTrue(viewModel.emailErrorState.value)
    }

    @Test
    fun `test isPasswordValid with valid password`() {
        // Given
        viewModel = SigInViewModel(sigIngUseCase, testDispatcher = testDispatcher)
        viewModel.passwordState.value = "StrongP@ss1"

        // When
        viewModel.isPasswordValid()

        // Then

        assertTrue(!viewModel.passwordErrorState.value)
    }

    @Test
    fun `test isPasswordValid with invalid password`() {
        // Given
        viewModel = SigInViewModel(sigIngUseCase, testDispatcher = testDispatcher)
        viewModel.passwordState.value = "weakpassword"

        // When
        viewModel.isPasswordValid()

        // Then
        assertTrue(viewModel.passwordErrorState.value)
    }

}

