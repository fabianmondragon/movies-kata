package com.example.katamovies.sigin.models

data class SigInUi(
    var showMessageFromSigUp: Boolean = false,
    var messageToShow: String = "",
    var showIsLoading: Boolean = false,
    var showErrorInField: Boolean = false,
    var goToMovies: Boolean = false
)