package com.example.domain.register.usescases

import app.cash.turbine.test
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import com.example.domain.register.repositories.SigUpRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class SigUpUseCaseTest {
    private val sigUpRepository : SigUpRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var sigUpUseCase: SigUpUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sigUpUseCase = SigUpUseCase(sigUpRepository, testDispatcher)

    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `test sigUpUser with success response`() = runBlockingTest  {
        // Given

        val userToRegister = UserD("TestUser", "test@example.com", "StrongP@ss1")

        val successResponse = ResultMovies.Success(userToRegister)
        coEvery { sigUpRepository.sigUpUser(userToRegister)} returns flowOf(successResponse)
        testDispatcher.scheduler.advanceUntilIdle()

        // When
        val result = sigUpUseCase.sigUpUser(userToRegister)
        testDispatcher.scheduler.advanceUntilIdle()
        delay(1000)
        // Then
        result.test{
            awaitComplete()
            val firsItem = awaitItem()

        }
    }
}