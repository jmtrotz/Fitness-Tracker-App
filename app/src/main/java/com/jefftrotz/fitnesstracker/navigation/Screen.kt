package com.jefftrotz.fitnesstracker.navigation

sealed class Screen(val route: String) {
    object AboutScreen : Screen("about_screen")
    object DetailsScreen : Screen("details_screen")
    object LoginScreen : Screen("login_screen")
    object MainScreen : Screen("main_screen")
    object SearchScreen : Screen("search_screen")
    object SettingsScreen : Screen("setting_screen")
    object SplashScreen : Screen("splash_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}