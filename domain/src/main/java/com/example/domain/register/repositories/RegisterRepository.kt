package com.example.domain.register.repositories

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    suspend fun userIsRegistered(userToRegister: UserD)
    suspend fun registerUser(userToRegister: UserD): Flow<ResultMovies<UserD, Exception>>
}