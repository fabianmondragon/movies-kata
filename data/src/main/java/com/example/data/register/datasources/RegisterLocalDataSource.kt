package com.example.data.register.datasources

import com.example.domain.register.dtos.User

interface RegisterLocalDataSource {

    suspend fun saveUser(user: User)
    suspend fun isRegistered(userToRegister: User)
    suspend fun registerLocal(userToRegister: User)
}