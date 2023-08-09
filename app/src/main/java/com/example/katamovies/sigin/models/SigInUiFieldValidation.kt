package com.example.katamovies.sigin.models

data class SigInUiFieldValidation(
    val emailState: String = "",
    val passwordState: String = "",
    val emailErrorState: String? = null,
    val passwordErrorState: String? = null
)
