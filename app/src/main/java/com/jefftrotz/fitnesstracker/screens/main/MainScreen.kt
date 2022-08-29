package com.jefftrotz.fitnesstracker.screens.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.components.AddButton
import com.jefftrotz.fitnesstracker.components.TopBar
import com.jefftrotz.fitnesstracker.model.Exercise
import com.jefftrotz.fitnesstracker.screens.main.MainViewModel

@Preview
@ExperimentalMaterial3Api
@Composable
fun MainScreen(){//navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopBar(
                title = "",
                icon = Icons.Default.Menu,
                contentDescription = stringResource(R.string.content_description_menu_button_icon)
            ) {
                /* TODO: Open navigation drawer */
            }
        },
        floatingActionButton = {
            AddButton { /* TODO: Open screen to start logging workout */ }
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {

        }
    }
}

@Composable
fun ExercisesList(exerciseList: List<Exercise>) {
    LazyColumn {
        items(exerciseList) { exercise ->
            ExerciseRow(exercise) {

            }
        }
    }
}

@Composable
fun ExerciseRow(exercise: Exercise,  onClick: () -> Unit) {
    Row {

    }
}