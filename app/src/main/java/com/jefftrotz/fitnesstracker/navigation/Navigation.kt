package com.jefftrotz.fitnesstracker.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

import com.jefftrotz.fitnesstracker.model.Exercise
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.ui.screens.AboutScreen
import com.jefftrotz.fitnesstracker.ui.screens.SearchScreen
import com.jefftrotz.fitnesstracker.ui.screens.SplashScreen
import com.jefftrotz.fitnesstracker.ui.screens.account.AccountScreen
import com.jefftrotz.fitnesstracker.ui.screens.account.CreateAccount
import com.jefftrotz.fitnesstracker.ui.screens.exercise.CreateExerciseScreen
import com.jefftrotz.fitnesstracker.ui.screens.exercise.EditExerciseScreen
import com.jefftrotz.fitnesstracker.ui.screens.main.MainScreen
import com.jefftrotz.fitnesstracker.ui.screens.workout.CreateWorkoutScreen
import com.jefftrotz.fitnesstracker.ui.screens.workout.EditWorkoutScreen
import com.squareup.moshi.Moshi

@Composable
@ExperimentalMaterial3Api
fun Navigation() {

    val navController = rememberNavController()

    // TODO Remove splash screen? Seems to be automatically provided by MaterialDesign 3?
    NavHost(
        startDestination = Screens.SplashScreen.route,
        navController = navController
    ) {

        composable(route = Screens.CreateExerciseScreen.route) {
            CreateExerciseScreen(navController = navController)
        }

        composable(route = Screens.CreateWorkoutScreen.route) {
            CreateWorkoutScreen(navController = navController)
        }

        composable(route = Screens.EditExerciseScreen.route) { backStackEntry ->
            val exerciseJson = backStackEntry.arguments?.getString("exercise")
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(Exercise::class.java).lenient()
            val exercise = exerciseJson?.let { json -> jsonAdapter.fromJson(json) }

            if (exercise != null) {
                EditExerciseScreen(navController = navController, exercise = exercise)
            }
        }

        composable(route = Screens.EditWorkoutScreen.route) { backStackEntry ->
            val workoutJson = backStackEntry.arguments?.getString("workout")
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(Workout::class.java).lenient()
            val workout = workoutJson?.let { json -> jsonAdapter.fromJson(json) }

            if (workout != null) {
                EditWorkoutScreen(navController = navController, workout = workout)
            }
        }

        navigation(startDestination = Screens.LoginScreen.route, route = "login") {
            composable(route = Screens.CreateAccount.route) {
                CreateAccount(navController = navController)
            }
        }

        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(route = Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }

        navigation(startDestination = Screens.SettingsScreen.route, route = "settings") {
            composable(route = Screens.AboutScreen.route) {
                AboutScreen(navController = navController)
            }
            composable(route = Screens.AccountScreen.route) {
                AccountScreen(navController = navController)
            }
        }

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
    }
}