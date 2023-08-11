package com.example.data.sigin.repository

import com.example.data.sigin.repository.datasources.impl.FirebaseSigInRemoteDataSourceImpl
import com.example.domain.register.ResultMovies
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class SigInRepositoryImplTest {

    private lateinit var  sigInRemoteDataSourceImpl: FirebaseSigInRemoteDataSourceImpl
    private lateinit var sigInRepositoryImpl: SigInRepositoryImpl

    @Before
    fun setup(){
        sigInRemoteDataSourceImpl = mockk()
        sigInRepositoryImpl = SigInRepositoryImpl(sigInRemoteDataSourceImpl)
    }

    @Test
    fun `test sigIn with correct values result success`() = runBlocking{
        // Given
        val result = ResultMovies.Success(true)
        coEvery { sigInRemoteDataSourceImpl.sigIn("email", "password" ) }returns  flowOf(result)
        // When
        val respond: Flow<ResultMovies<Boolean, Exception>> = sigInRepositoryImpl.sigIn(email = "email", password = "password")
        // Then
        val resultList = respond.toList()
        assertEquals(1, resultList.size)
        assertTrue(resultList[0] is ResultMovies.Success)
        assertEquals(true, (resultList[0] as ResultMovies.Success).value)

    }
}