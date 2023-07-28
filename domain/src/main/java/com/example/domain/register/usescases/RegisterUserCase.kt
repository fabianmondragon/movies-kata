package com.example.domain.register.usescases

import com.example.domain.register.dtos.User
import com.example.domain.register.repositories.RegisterRepository
import javax.inject.Inject

class RegisterUserCase @Inject constructor(
    private val repository: RegisterRepository){

    suspend fun registerUser(user: User){
        repository.userIsRegistered(user)
        repository.registerUser(user)
    }
}