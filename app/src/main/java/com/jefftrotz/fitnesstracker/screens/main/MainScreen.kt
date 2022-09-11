package com.jefftrotz.fitnesstracker.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.components.AddButton
import com.jefftrotz.fitnesstracker.components.ItemList
import com.jefftrotz.fitnesstracker.components.ListItem
import com.jefftrotz.fitnesstracker.components.TopBar
import com.jefftrotz.fitnesstracker.navigation.Screen
import com.jefftrotz.fitnesstracker.util.formatDate

@ExperimentalMaterial3Api
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val workoutList = viewModel.workoutList.collectAsState().value

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.main_top_bar_title),
                isMainScreen = true
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.drop_down_menu_about))
                    },
                    onClick = {
                        navController.navigate(Screen.AboutScreen.route)
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.drop_down_menu_settings))
                    },
                    onClick = {
                        navController.navigate(Screen.SettingsScreen.route)
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.drop_down_menu_logout))
                    },
                    onClick = {
                        // TODO: Show logout confirmation dialog
                    }
                )
            }
        },
        floatingActionButton = {
            AddButton {
                navController.navigate(Screen.WorkoutDetailsScreen.route)
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            if (workoutList.isEmpty()) {
                Text(text = stringResource(id = R.string.workout_list_empty))
            } else {
                ItemList(workoutList) { workout ->
                    val details = HashMap<String, String>()
                    if (workout.description.isNotBlank()) {
                        details[stringResource(id = R.string.workout_description)] = workout.description
                    }

                    ListItem(
                        itemName = workout.name,
                        itemDetails = formatDate(workout.date.time),
                        expandedDetails = details
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.drop_down_menu_edit))
                            },
                            onClick = {
                                navController.navigate(Screen.WorkoutDetailsScreen.withArgs(
                                    workout.id.toString()))
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.drop_down_menu_delete))
                            },
                            onClick = {
                                // TODO: Show delete confirmation dialog
                            }
                        )
                    }
                }
            }
        }
    }
}