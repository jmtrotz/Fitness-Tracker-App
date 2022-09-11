package com.jefftrotz.fitnesstracker.screens.workoutDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.components.*
import com.jefftrotz.fitnesstracker.data.DataWrapper
import com.jefftrotz.fitnesstracker.model.Workout
import java.util.*
import kotlin.collections.HashMap

@ExperimentalMaterial3Api
@Composable
fun WorkoutDetailsScreen(
    navController: NavController,
    viewModel: WorkoutDetailsViewModel,
    id: String?
) {
    val workout = produceState<DataWrapper<Workout>>(initialValue = DataWrapper.Loading()) {
        value = viewModel.getWorkoutById(UUID.fromString(id))
    }.value

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.details_top_bar_title)
            ) {
                //navController.popBackStack()
            }
        },
        floatingActionButton = {
            AddButton {
                /* TODO: Add exercise */
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            if (workout.data == null) {
                CircularProgressIndicator()
            } else {
                WorkoutDetails(workout = workout.data)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun WorkoutDetails(workout: Workout) {
    var workoutName by remember {
        mutableStateOf(workout.name)
    }
    var workoutDescription by remember {
        mutableStateOf(workout.description)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CommonTextField(
            value = workoutName,
            onValueChange = { newValue ->
                workoutName = newValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = "Name",
            isError = workoutName.isBlank(),
            errorMessage = "Please enter a name"
        )

        CommonTextField(
            value =workoutDescription,
            onValueChange = { newValue ->
                workoutDescription = newValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = "Description"
        )

        ItemList(list = workout.exerciseList) { exercise ->
            val details = HashMap<String, Any>()
            if (exercise.description.isNotBlank()) {
                details[stringResource(id = R.string.workout_description)] = exercise.description
            }
            if (exercise.distance > 0.0) {
                details[stringResource(id = R.string.workout_distance)] = exercise.distance
            }
            if (exercise.weight > 0.0) {
                details[stringResource(id = R.string.workout_weight)] = exercise.weight
            }
            if (exercise.repetitions > 0) {
                details[stringResource(id = R.string.workout_repetitions)] = exercise.repetitions
            }

            ListItem(
                itemName = exercise.name,
                itemDetails = exercise.type.toString(),
                expandedDetails = details
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.drop_down_menu_edit))
                    },
                    onClick = {
                        // TODO: Open screen to edit an exercise
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(id = R.string.drop_down_menu_delete))
                    },
                    onClick = {
                        // TODO: Show confirmation dialog
                    }
                )
            }
        }
    }
}