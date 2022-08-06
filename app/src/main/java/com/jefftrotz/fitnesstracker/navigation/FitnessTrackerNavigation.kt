package com.jefftrotz.fitnesstracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.jefftrotz.fitnesstracker.screens.AboutScreen
import com.jefftrotz.fitnesstracker.screens.CreateAccountScreen
import com.jefftrotz.fitnesstracker.screens.LoginScreen
import com.jefftrotz.fitnesstracker.screens.MainScreen
import com.jefftrotz.fitnesstracker.screens.SearchScreen
import com.jefftrotz.fitnesstracker.screens.SettingsScreen
import com.jefftrotz.fitnesstracker.screens.SplashScreen

@Composable
fun FitnessTrackerNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FitnessTrackerScreens.SplashScreen.name
    ) {
        composable(FitnessTrackerScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        composable(FitnessTrackerScreens.CreateAccountScreen.name) {
            CreateAccountScreen(navController = navController)
        }

        composable(FitnessTrackerScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(FitnessTrackerScreens.MainScreen.name) {
            MainScreen(navController = navController)
        }

        composable(FitnessTrackerScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(FitnessTrackerScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }

        composable(FitnessTrackerScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
    }
}