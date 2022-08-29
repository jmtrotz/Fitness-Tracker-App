package com.jefftrotz.fitnesstracker.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.jefftrotz.fitnesstracker.screens.AboutScreen
import com.jefftrotz.fitnesstracker.screens.login.LoginScreen
import com.jefftrotz.fitnesstracker.screens.main.MainScreen
import com.jefftrotz.fitnesstracker.screens.SearchScreen
import com.jefftrotz.fitnesstracker.screens.SettingsScreen
import com.jefftrotz.fitnesstracker.screens.SplashScreen
import com.jefftrotz.fitnesstracker.screens.login.LoginViewModel
import com.jefftrotz.fitnesstracker.screens.main.MainViewModel

@ExperimentalMaterial3Api
@Composable
fun FitnessTrackerNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FitnessTrackerScreens.SplashScreen.name
    ) {
        composable(FitnessTrackerScreens.AboutScreen.name) {
            AboutScreen(navController)
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