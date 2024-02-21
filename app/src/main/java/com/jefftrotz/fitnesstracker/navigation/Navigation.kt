package com.jefftrotz.fitnesstracker.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation

import com.jefftrotz.fitnesstracker.model.Workout.Companion.workoutFromString
import com.jefftrotz.fitnesstracker.ui.screens.AboutScreen
import com.jefftrotz.fitnesstracker.ui.screens.SearchScreen
import com.jefftrotz.fitnesstracker.ui.screens.SplashScreen
import com.jefftrotz.fitnesstracker.ui.screens.account.AccountScreen
import com.jefftrotz.fitnesstracker.ui.screens.account.CreateAccount
import com.jefftrotz.fitnesstracker.ui.screens.exercise.ExerciseDetailsScreen
import com.jefftrotz.fitnesstracker.ui.screens.main.MainScreen
import com.jefftrotz.fitnesstracker.ui.screens.workout.WorkoutDetailsScreen
import com.jefftrotz.fitnesstracker.util.Constants

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Navigation() {
    val navController = rememberNavController()

    // TODO Remove splash screen?. Seems to be automatically provided by MaterialDesign 3?
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        navigation(startDestination = Screens.LoginScreen.route, route = "login") {
            composable(Screens.CreateAccount.route) {
                CreateAccount(navController = navController)
            }
        }

        navigation(startDestination = Screens.SettingsScreen.route, route = "settings") {
            composable(Screens.AboutScreen.route) {
                AboutScreen(navController = navController)
            }
            composable(Screens.AccountScreen.route) {
                AccountScreen(navController = navController)
            }
        }

        composable(Screens.ExerciseDetailsScreen.route) {
            ExerciseDetailsScreen(navController = navController)
        }

        composable(Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }

        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(
            route = Screens.WorkoutDetailsScreen.route + "?${Constants.WORKOUT_ID_KEY}={${Constants.WORKOUT_ID_KEY}}",
            arguments = listOf(
                navArgument(Constants.WORKOUT_ID_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { entry ->
            val args = entry.arguments?.getString(Constants.WORKOUT_ID_KEY)
            if (args != null) {
                val workout = args.workoutFromString()
                WorkoutDetailsScreen(navController = navController, workout = workout)
            }
        }
    }
}