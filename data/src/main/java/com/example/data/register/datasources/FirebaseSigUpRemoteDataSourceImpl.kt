package com.example.data.register.datasources

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSigUpRemoteDataSourceImpl
@Inject constructor() : SigUpRemoteDataSource {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun isSigUpInFirebase(userToRegister: UserD) {

    }

    override suspend fun sigUpInFirebase(userToRegister: UserD): Flow<ResultMovies<UserD, Exception>>
    = flow {
        try {
            val authResult = auth.createUserWithEmailAndPassword(
                userToRegister.email,
                userToRegister.password
            ).await()
            if (authResult != null) {
                val firebaseUser = authResult.user
                val userD = UserD(
                    userName = firebaseUser?.displayName ?: "",
                    email = firebaseUser?.email ?: "",
                    photo = firebaseUser?.photoUrl.toString(),
                    idFirebase = firebaseUser?.uid ?: ""
                )
                emit(ResultMovies.Success(userD))

            } else {
                emit(ResultMovies.Error(Exception()))
            }

        } catch (e: Exception) {
            println("${e.message}")
            emit(ResultMovies.Error(e))
        }
    }
}
