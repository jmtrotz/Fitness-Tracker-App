package com.jefftrotz.fitnesstracker.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.components.AddButton
import com.jefftrotz.fitnesstracker.components.TopBar
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.navigation.FitnessTrackerScreens
import com.jefftrotz.fitnesstracker.util.formatDate

@ExperimentalMaterial3Api
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val workoutList = viewModel.workoutList.collectAsState().value

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.top_bar_title),
                icon = Icons.Default.Menu,
                contentDescription = stringResource(R.string.content_description_menu_button_icon)
            ) {
                /* TODO: Open navigation drawer */
            }
        },
        floatingActionButton = {
            AddButton {
                /* TODO: Open screen to create a workout */
            }
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            WorkoutList(navController, workoutList)
        }
    }
}

@Composable
fun WorkoutList(navController: NavController, workoutList: List<Workout>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = workoutList) { workout ->
            WorkoutItem(workout) {
                navController.navigate(FitnessTrackerScreens.DetailsScreen.name)
            }
        }
    }
}

@Composable
fun WorkoutItem(workout: Workout, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = workout.name,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 2.dp),
            fontSize = 20.sp
        )

        Text(
            text = formatDate(workout.date.time),
            modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 8.dp),
            fontSize = 16.sp
        )
    }
}