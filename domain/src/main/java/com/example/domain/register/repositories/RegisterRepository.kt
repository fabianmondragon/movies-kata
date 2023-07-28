package com.example.domain.register.repositories

import com.example.domain.register.dtos.User

interface RegisterRepository {

    suspend fun userIsRegistered(userToRegister: User)
    suspend fun registerUser(userToRegister: User)
}