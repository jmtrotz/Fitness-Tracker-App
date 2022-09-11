package com.jefftrotz.fitnesstracker.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jefftrotz.fitnesstracker.screens.*
import com.jefftrotz.fitnesstracker.screens.workoutDetails.WorkoutDetailsScreen
import com.jefftrotz.fitnesstracker.screens.workoutDetails.WorkoutDetailsViewModel
import com.jefftrotz.fitnesstracker.screens.login.LoginScreen
import com.jefftrotz.fitnesstracker.screens.main.MainScreen
import com.jefftrotz.fitnesstracker.screens.login.LoginViewModel
import com.jefftrotz.fitnesstracker.screens.main.MainViewModel

@ExperimentalMaterial3Api
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
        // Splash screen commented out for now. One seems to be
        // automatically provided for me by MaterialDesign 3?
        //Screen.SplashScreen.name
    ) {
        composable(Screen.AboutScreen.route) {
            AboutScreen(navController)
        }

        composable(
            route = Screen.WorkoutDetailsScreen.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { entry ->
            val viewModel = hiltViewModel<WorkoutDetailsViewModel>()
            WorkoutDetailsScreen(
                navController = navController,
                viewModel = viewModel,
                id = entry.arguments?.getString("id")
            )
        }

        composable(Screen.LoginScreen.route) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(navController, viewModel)
        }

        composable(Screen.MainScreen.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController, viewModel)
        }

        composable(Screen.SearchScreen.route) {
            SearchScreen(navController)
        }

        composable(Screen.SettingsScreen.route) {
            SettingsScreen(navController)
        }

        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
    }
}