package com.example.domain.register.dtos

data class UserD(
    val userName: String = "",
    val password: String = "",
    val email: String = "",
    val firebaseUser: String = "",
    val nameFirebase: String = "",
    val photo: String = "",
    val idFirebase: String = ""
)