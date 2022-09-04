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
import com.jefftrotz.fitnesstracker.screens.details.DetailsScreen
import com.jefftrotz.fitnesstracker.screens.login.LoginScreen
import com.jefftrotz.fitnesstracker.screens.main.MainScreen
import com.jefftrotz.fitnesstracker.screens.login.LoginViewModel
import com.jefftrotz.fitnesstracker.screens.main.MainViewModel

@ExperimentalMaterial3Api
@Composable
fun FitnessTrackerNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FitnessTrackerScreens.LoginScreen.name
        // Splash screen commented out for now. One seems to be
        // automatically provided for me by MaterialDesign 3?
        //FitnessTrackerScreens.SplashScreen.name
    ) {
        composable(FitnessTrackerScreens.AboutScreen.name) {
            AboutScreen(navController)
        }

        val argName = "id"
        val detailsScreenName = FitnessTrackerScreens.DetailsScreen.name
        composable(
            route = "$detailsScreenName/{$argName",
            arguments = listOf(navArgument(argName) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(argName).let { workoutId ->
                DetailsScreen(
                    navController = navController,
                    workoutId = workoutId.toString()
                )
            }
        }

        composable(FitnessTrackerScreens.LoginScreen.name) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(navController, viewModel)
        }

        composable(FitnessTrackerScreens.MainScreen.name) {
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen()//navController, viewModel)
        }

        composable(FitnessTrackerScreens.SearchScreen.name) {
            SearchScreen(navController)
        }

        composable(FitnessTrackerScreens.SettingsScreen.name) {
            SettingsScreen(navController)
        }

        composable(FitnessTrackerScreens.SplashScreen.name) {
            SplashScreen(navController)
        }
    }
}