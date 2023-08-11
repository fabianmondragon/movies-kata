package com.example.data.sigin.repository.datasources.impl

import com.example.domain.register.ResultMovies
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.internal.zzr
import io.mockk.coEvery
import org.junit.Before
import io.mockk.mockk
import junit.framework.TestResult
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals


class FirebaseSigInRemoteDataSourceImplTest {

    lateinit var auth: FirebaseAuth
    lateinit var firebaseSigInRemoteDataSourceImpl: FirebaseSigInRemoteDataSourceImpl

    @Before
    fun setup(){
        auth = mockk(relaxed = true)
        firebaseSigInRemoteDataSourceImpl = FirebaseSigInRemoteDataSourceImpl()
    }

    @Test
    fun `sigIn with email and password and result is ok`() = runBlocking{
        // Given
        val email = "email"
        val password = "password"
        val authResult = mockk<Task<AuthResult>>()
        val deferred = CompletableDeferred(authResult)

        coEvery { auth.signInWithEmailAndPassword(email, password) } returns authResult

        // When
        val resultFlow = firebaseSigInRemoteDataSourceImpl.sigIn(email, password)
        resultFlow.collect { response ->
            assertEquals(ResultMovies.Success(true), response)
        }

    }
}
