package com.example.domain.register.repositories

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import kotlinx.coroutines.flow.Flow

interface SigUpRepository {

    suspend fun userIsSigUp(userToRegister: UserD)

    suspend fun sigUpUser(userToRegister: UserD): Flow<ResultMovies<UserD, Exception>>
}