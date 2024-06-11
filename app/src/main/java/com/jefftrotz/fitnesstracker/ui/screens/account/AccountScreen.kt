package com.jefftrotz.fitnesstracker.ui.screens.account

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AccountScreen(navController: NavController) {

    val snackbarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<AccountViewModel>()

    LaunchedEffect(key1 = coroutineScope.coroutineContext) {
        viewModel.getSideEffectFlow().collect { message ->
            snackbarState.showSnackbar(message = message)
        }
    }
}