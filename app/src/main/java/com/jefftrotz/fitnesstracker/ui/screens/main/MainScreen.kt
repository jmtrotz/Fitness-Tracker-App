package com.jefftrotz.fitnesstracker.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.navigation.Screens
import com.jefftrotz.fitnesstracker.ui.components.AddButton
import com.jefftrotz.fitnesstracker.ui.components.ItemList
import com.jefftrotz.fitnesstracker.ui.components.ListItem
import com.jefftrotz.fitnesstracker.ui.components.ShowDialog
import com.jefftrotz.fitnesstracker.ui.components.TopBar

import com.jefftrotz.fitnesstracker.model.states.LoadingState
import com.jefftrotz.fitnesstracker.model.states.MainState
import com.jefftrotz.fitnesstracker.util.formatDate

import java.util.ArrayList

@Composable
@ExperimentalMaterial3Api
fun MainScreen(navController: NavController) {

    val snackbarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<MainViewModel>()
    val uiState = viewModel.getUIStateFlow().collectAsState()

    when (uiState.value) {
        is MainState -> {
            val newState = uiState.value as MainState
            RenderScreen(navController = navController, state = newState)
        }
        is LoadingState -> CircularProgressIndicator()
    }

    LaunchedEffect(key1 = coroutineScope.coroutineContext) {
        viewModel.getSideEffectFlow().collect { message ->
            snackbarState.showSnackbar(message = message)
        }
    }
}

@Composable
@ExperimentalMaterial3Api
fun RenderScreen(navController: NavController, state: MainState) {

    var showLogoutDialog by remember { mutableStateOf(false) }
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.main_top_bar_title),
                isMainScreen = true
            ) {
                IconButton(
                    onClick = {
                        isMenuExpanded = !isMenuExpanded
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.content_description_menu_button_icon)
                    )
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = {
                            isMenuExpanded = !isMenuExpanded
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.drop_down_menu_about))
                            },
                            onClick = {
                                navController.navigate(Screens.AboutScreen.route)
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.drop_down_menu_settings))
                            },
                            onClick = {
                                navController.navigate(Screens.SettingsScreen.route)
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.drop_down_menu_logout))
                            },
                            onClick = {
                                showLogoutDialog = true
                            }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            AddButton {
                navController.navigate(Screens.CreateWorkoutScreen.route)
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            if (state.workouts.isEmpty()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.workout_list_empty))
                }
            } else {
                ItemList(state.workouts) { workout ->
                    val details = ArrayList<String>()
                    if (workout.description.isNotBlank()) {
                        details.add(stringResource(id = R.string.workout_description, workout.description))
                    }

                    ListItem(
                        itemName = workout.name,
                        itemDetails = formatDate(workout.date.time),
                        expandedDetails = details
                    ) {
                        navController.navigate(Screens.EditWorkoutScreen.route)
                    }
                }

                if (showLogoutDialog) {
                    ShowDialog(
                        text = stringResource(id = R.string.logout_dialog_text),
                        onDismiss = {
                            showLogoutDialog = false
                        }) {
                        navController.navigate(Screens.LoginScreen.route) {
                            popUpTo(Screens.MainScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }
}