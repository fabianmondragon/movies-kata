package com.example.domain.register.repositories

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import kotlinx.coroutines.flow.Flow

interface SigUpRepository {

    suspend fun userIsSigUp(userToRegister: UserDto)

    suspend fun sigUpUser(userToRegister: UserDto): Flow<ResultMovies<UserDto, Exception>>
}