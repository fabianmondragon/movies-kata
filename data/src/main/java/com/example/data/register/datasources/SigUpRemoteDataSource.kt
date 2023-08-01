package com.example.data.register.datasources

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import kotlinx.coroutines.flow.Flow

interface SigUpRemoteDataSource {

    suspend fun isSigUpInFirebase(userToRegister: UserDto)
    suspend fun sigUpInFirebase(userToRegister: UserDto): Flow<ResultMovies<UserDto, Exception>>

}