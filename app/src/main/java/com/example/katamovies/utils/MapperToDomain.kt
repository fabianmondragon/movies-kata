package com.example.katamovies.utils

import com.example.domain.register.dtos.UserD

fun convertParamsToUserD(userName: String, email: String, password: String): UserD {
    return UserD(userName = userName, password = password, email = email)
}