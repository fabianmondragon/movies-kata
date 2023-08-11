package com.example.katamovies.sigup.provider

import com.example.domain.register.dtos.UserD

fun createUserD(
    userName: String = "TestUser",
    email: String = "test@example.com",
    password: String = "StrongP@ss1"
): UserD {
    return UserD(userName, email, password)
}