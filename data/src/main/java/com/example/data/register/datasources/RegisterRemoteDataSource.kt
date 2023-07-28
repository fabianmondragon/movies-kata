package com.example.data.register.datasources

import com.example.domain.register.dtos.User

interface RegisterRemoteDataSource {

    suspend fun isRegisteredInFirebase(userToRegister: User)
    suspend fun registerInFirabase(userToRegister: User)

}