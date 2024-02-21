package com.jefftrotz.fitnesstracker.ui.screens.workout

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.model.intents.DeleteWorkout
import com.jefftrotz.fitnesstracker.model.intents.UpdateWorkout
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
fun WorkoutDetailsScreen(navController: NavController, workout: Workout) {

    val viewModel: BaseViewModel by viewModels()
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.details_top_bar_title),
                isMainScreen = false,
                onClick = {
                    viewModel.setUserIntent(UpdateWorkout(workout = workout))
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
                navController.navigate(Screens.ExerciseDetailsScreen.route)
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            if (!viewModel.isNewWorkout && viewModel.workoutName.value.text.isBlank()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {
                WorkoutDetails(navController = navController, viewModel)
                if (showDeleteDialog) {
                    ShowDialog(
                        text = stringResource(id = R.string.delete_dialog_text),
                        onDismiss = {
                            showDeleteDialog = false
                        }
                    ) {
                        showDeleteDialog = false
                        viewModel.setUserIntent(DeleteWorkout(workout = workout))
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun WorkoutDetails(navController: NavController, viewModel: WorkoutDetailsViewModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CommonTextField(
            value = viewModel.workoutName.value.text,
            onValueChange = { newValue ->
                viewModel.workoutName.value.text = newValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = stringResource(id = R.string.placeholder_name),
            isError = viewModel.workoutName.value.text.isBlank(),
            errorMessage = stringResource(id = R.string.error_workout_name_blank)
        )

        CommonTextField(
            value = viewModel.workoutDescription.value.text,
            onValueChange = { newValue ->
                viewModel.workoutDescription.value.text = newValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = stringResource(id = R.string.placeholder_description)
        )

        ItemList(list = viewModel.currentWorkout.exerciseList) { exercise ->
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
                navController.navigate(Screens.ExerciseDetailsScreen.route)
            }
        }
    }
}