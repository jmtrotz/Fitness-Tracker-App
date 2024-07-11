package com.jefftrotz.fitnesstracker.ui.screens.workout

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.model.actions.DeleteWorkout
import com.jefftrotz.fitnesstracker.model.actions.UpdateWorkout
import com.jefftrotz.fitnesstracker.navigation.Screens
import com.jefftrotz.fitnesstracker.ui.components.AddButton
import com.jefftrotz.fitnesstracker.ui.components.CommonTextField
import com.jefftrotz.fitnesstracker.ui.components.ItemList
import com.jefftrotz.fitnesstracker.ui.components.ListItem
import com.jefftrotz.fitnesstracker.ui.components.ShowDialog
import com.jefftrotz.fitnesstracker.ui.components.TopBar
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel

@ExperimentalMaterial3Api
@Composable
fun EditWorkoutScreen(navController: NavController, workout: Workout) {

    val snackbarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<WorkoutViewModel>()
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = coroutineScope.coroutineContext) {
        viewModel.getSideEffectFlow().collect { message ->
            snackbarState.showSnackbar(message = message)
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.details_top_bar_title),
                isMainScreen = false,
                onClick = {
                    viewModel.setUserAction(UpdateWorkout(workout = workout))
                    navController.popBackStack()
                }
            ) {
                IconButton(
                    onClick = {
                        Log.d("WorkoutDetailsScreen", "Showing delete dialog")
                        showDeleteDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.content_description_delete_button_icon)
                    )
                }
            }
        },
        floatingActionButton = {
            AddButton {
                navController.navigate(Screens.EditExerciseScreen.route)
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            WorkoutDetails(
                navController = navController,
                viewModel = viewModel,
                workout = workout
            )

            if (showDeleteDialog) {
                ShowDialog(
                    text = stringResource(id = R.string.delete_dialog_text),
                    onDismiss = {
                        showDeleteDialog = false
                    }
                ) {
                    showDeleteDialog = false
                    viewModel.setUserAction(DeleteWorkout(workout = workout))
                    navController.popBackStack()
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun WorkoutDetails(
    navController: NavController,
    viewModel: BaseViewModel,
    workout: Workout
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CommonTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            errorMessage = stringResource(id = R.string.error_workout_name_blank),
            label = stringResource(id = R.string.placeholder_name),
            isError = workout.name.isBlank(),
            value = workout.name,
            onValueChange = { newName ->
                workout.name = newName
                viewModel.setUserAction(action = UpdateWorkout(workout = workout))
            }
        )

        CommonTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = stringResource(id = R.string.placeholder_description),
            value = workout.description,
            onValueChange = { newDescription ->
                workout.description = newDescription
                viewModel.setUserAction(action = UpdateWorkout(workout = workout))
            }
        )

        ItemList(list = workout.exercises) { exercise ->
            val details = ArrayList<String>()
            if (exercise.description.isNotBlank()) {
                details.add(stringResource(id = R.string.workout_description, exercise.description))
            }
            if (exercise.distance > 0.0) {
                details.add(stringResource(id = R.string.workout_distance, exercise.distance))
            }
            if (exercise.weight > 0.0) {
                details.add(stringResource(id = R.string.workout_weight, exercise.weight))
            }
            if (exercise.repetitions > 0) {
                details.add(stringResource(id = R.string.workout_repetitions, exercise.repetitions))
            }

            ListItem(
                itemName = exercise.name,
                itemDetails = exercise.type.toString(),
                expandedDetails = details
            ) {
                navController.navigate(Screens.EditExerciseScreen.route)
            }
        }
    }
}