package com.example.katamovies.sigin.models

data class ResultSigUpUi(
    var showIsLoading: Boolean = false,
    var answered: Boolean = false,
    var isSuccess: Boolean = false,
    var messageToShow: String = ""
)