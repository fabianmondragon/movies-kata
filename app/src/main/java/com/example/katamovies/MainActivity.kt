package com.example.katamovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.katamovies.movies.MoviesFragment
import com.example.katamovies.signup.SigInFragment
import com.example.katamovies.ui.theme.KatamoviesTheme
import com.example.katamovies.utils.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KatamoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setupNavigation()
                }
            }
        }
    }
}

@Composable
fun setupNavigation() {
    var navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.SigIn.route
    ) {
        composable(
            route = Route.SigIn.route
        )
        {
            goToSigIn(navController)
        }
        composable(
            route = Route.List.route
        ) {
            goToMoviesFragment(navController)
        }
    }
}

@Composable
fun goToMoviesFragment(navController: NavHostController) {
    MoviesFragment(navController = navController)
}

@Composable
fun goToSigIn(navController: NavController) {
    SigInFragment(navController)
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KatamoviesTheme {
        Greeting("Android")
    }
}