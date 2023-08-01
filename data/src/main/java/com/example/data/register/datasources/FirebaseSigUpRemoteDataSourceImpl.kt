package com.example.data.register.datasources

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseSigUpRemoteDataSourceImpl
@Inject constructor() : SigUpRemoteDataSource {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun isSigUpInFirebase(userToRegister: UserDto) {

    }

    override suspend fun sigUpInFirebase(userToRegister: UserDto): Flow<ResultMovies<UserDto, Exception>>
    = flow {
        try {
            val authResult = auth.createUserWithEmailAndPassword(
                userToRegister.email,
                userToRegister.password
            ).await()
            if (authResult != null) {
                val firebaseUser = authResult.user
                val userDto = UserDto(
                    userName = firebaseUser?.displayName ?: "",
                    email = firebaseUser?.email ?: "",
                    photo = firebaseUser?.photoUrl.toString(),
                    idFirebase = firebaseUser?.uid ?: ""
                )
                emit(ResultMovies.Success(userDto))

            } else {
                emit(ResultMovies.Error(Exception()))
            }

        } catch (e: Exception) {
            println("${e.message}")
            emit(ResultMovies.Error(e))
        }
    }
}
