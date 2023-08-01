package com.example.katamovies.utils

sealed class Route (val route: String) {
    object SigIn: Route("SigIn")
    object SigUp: Route("SigUp")
    object List: Route("List")
}