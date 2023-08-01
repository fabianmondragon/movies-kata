package com.example.domain.register.repositories

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    suspend fun userIsRegistered(userToRegister: UserDto)
    suspend fun registerUser(userToRegister: UserDto): Flow<ResultMovies<UserDto, Exception>>
}