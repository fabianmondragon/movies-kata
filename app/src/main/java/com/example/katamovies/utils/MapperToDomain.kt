package com.example.katamovies.utils

import com.example.domain.register.dtos.UserD

class MapperToDomain {
}

fun convertParamsToUserD(userName: String, email: String, password: String): UserD {
    return UserD(userName = userName, password = password, email = email)
}