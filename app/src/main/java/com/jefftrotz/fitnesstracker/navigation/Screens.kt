package com.jefftrotz.fitnesstracker.navigation

sealed class Screens(val route: String) {

    object AboutScreen: Screens(route = "about")
    object AccountScreen: Screens(route = "account")
    object CreateAccount: Screens(route = "create_account")
    object ExerciseDetailsScreen: Screens(route = "exercise_details")
    object LoginScreen: Screens(route = "login")
    object MainScreen: Screens(route = "main")
    object SearchScreen: Screens(route = "search")
    object SettingsScreen: Screens(route = "setting")
    object SplashScreen: Screens(route = "splash")
    object WorkoutDetailsScreen: Screens(route = "workout_details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}