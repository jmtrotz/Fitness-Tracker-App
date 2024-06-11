package com.jefftrotz.fitnesstracker.navigation

sealed class Screens(val route: String) {

    object AboutScreen: Screens(route = "about")
    object AccountScreen: Screens(route = "account")
    object CreateAccount: Screens(route = "create_account")
    object CreateExerciseScreen: Screens(route = "create_exercise")
    object EditExerciseScreen: Screens(route = "edit_exercise")
    object CreateWorkoutScreen: Screens(route = "create_workout")
    object EditWorkoutScreen: Screens(route = "edit_workout")
    object LoginScreen: Screens(route = "login")
    object MainScreen: Screens(route = "main")
    object SearchScreen: Screens(route = "search")
    object SettingsScreen: Screens(route = "setting")
    object SplashScreen: Screens(route = "splash")
}