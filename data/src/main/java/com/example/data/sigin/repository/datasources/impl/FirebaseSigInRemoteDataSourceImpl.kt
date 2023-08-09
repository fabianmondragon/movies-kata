package com.example.data.sigin.repository.datasources.impl

import com.example.data.sigin.repository.datasources.definition.SigInRemoteDataSource
import com.example.domain.register.ResultMovies
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirebaseSigInRemoteDataSourceImpl @Inject constructor(): SigInRemoteDataSource {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun sigIn(email: String, password: String): Flow<ResultMovies<Boolean, Exception>> = flow {
        try {
            val result = auth.signInWithEmailAndPassword(email, password)
                .await()
            if (result!= null) {
                emit(ResultMovies.Success(true))
            } else {
                emit(ResultMovies.Success(false))
            }
        } catch (firebaseAuthInvalid: FirebaseAuthInvalidUserException){
            emit(ResultMovies.Error(firebaseAuthInvalid))
        } catch (firebaseAuthInvalidCredential: FirebaseAuthInvalidCredentialsException){
            emit(ResultMovies.Error(firebaseAuthInvalidCredential))
        } catch (anyException: FirebaseAuthException ){
            emit(ResultMovies.Error(anyException))
        }


    }
}