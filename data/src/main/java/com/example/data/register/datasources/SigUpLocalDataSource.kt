package com.example.data.register.datasources

import com.example.domain.register.dtos.UserDto

interface SigUpLocalDataSource {

    suspend fun saveUser(user: UserDto)
    suspend fun isRegistered(userToRegister: UserDto)
    suspend fun registerLocal(userToRegister: UserDto)
}