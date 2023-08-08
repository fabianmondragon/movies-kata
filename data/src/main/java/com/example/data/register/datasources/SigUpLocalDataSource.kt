package com.example.data.register.datasources

import com.example.domain.register.dtos.UserD

interface SigUpLocalDataSource {

    suspend fun saveUser(user: UserD)
    suspend fun isRegistered(userToRegister: UserD)
    suspend fun registerLocal(userToRegister: UserD)
}