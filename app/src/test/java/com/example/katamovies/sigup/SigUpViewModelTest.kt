package com.example.katamovies.sigup

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import com.example.domain.register.usescases.SigUpUseCase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoAnnotations
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class SigUpViewModelTest {
    private var sigUpUseCase: SigUpUseCase = mockk()
    private lateinit var sigUpViewModel: SigUpViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sigUpViewModel = SigUpViewModel(sigUpUseCase, testDispatcher)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test sigUpUser with success response`() = runTest {
        // Given
        val userName = "TestUser"
        val email = "test@example.com"
        val password = "StrongP@ss1"
        coEvery { sigUpUseCase.sigUpUser(any()) } returns flowOf(any())

        // When
        sigUpViewModel.sigUpUser(userName, email, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val resultListSigUpUiValue = sigUpViewModel.resultListSigUpUi.value
        assertEquals(false, resultListSigUpUiValue.showIsLoading)
        assertEquals(true, resultListSigUpUiValue.answered)
        assertEquals(false, resultListSigUpUiValue.isSuccess)
        assertEquals("Occurrio un error", resultListSigUpUiValue.messageToShow)

    }

    @Test
    fun `test sigUpUser with error response`() = runTest {
        // Given
        val userName = "TestUser"
        val email = "test@example.com"
        val password = "WeakPassword"
        val errorResponse = ResultMovies.Error(Exception("Some error"))
        coEvery { sigUpUseCase.sigUpUser(any()) } returns flowOf(errorResponse)

        // When
        sigUpViewModel.sigUpUser(userName, email, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val resultListSigUpUiValue = sigUpViewModel.resultListSigUpUi.value
        assertEquals(false, resultListSigUpUiValue.showIsLoading)
        assertEquals(true, resultListSigUpUiValue.answered)
        assertEquals(false, resultListSigUpUiValue.isSuccess)
        assertEquals("Occurrio un error", resultListSigUpUiValue.messageToShow)
    }
}