package com.jefftrotz.fitnesstracker.ui.screens.workout

import androidx.lifecycle.viewModelScope
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.model.actions.CreateWorkout
import com.jefftrotz.fitnesstracker.model.actions.DeleteWorkout
import com.jefftrotz.fitnesstracker.model.actions.UpdateWorkout

import com.jefftrotz.fitnesstracker.repository.Repository
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel
import com.jefftrotz.fitnesstracker.viewmodel.StringProvider

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val repository: Repository
): BaseViewModel() {

    init {
        viewModelScope.launch {
            super.getUserActionFlow().collect { action ->
                when (action) {
                    is CreateWorkout -> createWorkout(action.workout)
                    is UpdateWorkout -> updateWorkout(action.workout)
                    is DeleteWorkout -> deleteWorkout(action.workout)
                }
            }
        }
    }

    private fun createWorkout(workout: Workout) {

    }

    private fun updateWorkout(workout: Workout) {

    }

    private fun deleteWorkout(workout: Workout) {

    }
}