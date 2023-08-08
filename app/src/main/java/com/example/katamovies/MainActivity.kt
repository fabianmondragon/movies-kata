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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.katamovies.movies.screen.MoviesFragment
import com.example.katamovies.sigin.SigInFragment
import com.example.katamovies.sigup.SigUpFragment
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
        startDestination = "${Route.SigIn.route}/{sigUpUser}?"
    ) {
        composable(
            route = "${Route.SigIn.route}/{sigUpUser}?",
            arguments = listOf(navArgument("sigUpUser") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val argValue = navBackStackEntry.arguments?.getString("sigUpUser")
            goToSigIn(navController, argValue)
        }

        composable(
            route = Route.SigUp.route,
        ) {
            goToSigUp(navController)
        }

        composable(
            route = Route.List.route
        ) {
            goToMoviesFragment(navController)
        }
    }
}

@Composable
private fun goToSigUp(navController: NavController) {
    SigUpFragment(navController)
}

@Composable
private fun goToMoviesFragment(navController: NavHostController) {
    MoviesFragment(navController = navController)
}

@Composable
private fun goToSigIn(navController: NavController, messageFromSigUp: String?) {
    SigInFragment(navController, messageFromSigUp = messageFromSigUp)
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