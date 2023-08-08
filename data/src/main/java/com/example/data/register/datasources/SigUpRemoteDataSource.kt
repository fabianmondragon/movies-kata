package com.example.data.register.datasources

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import kotlinx.coroutines.flow.Flow

interface SigUpRemoteDataSource {

    suspend fun isSigUpInFirebase(userToRegister: UserD)
    suspend fun sigUpInFirebase(userToRegister: UserD): Flow<ResultMovies<UserD, Exception>>

}