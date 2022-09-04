package com.jefftrotz.fitnesstracker.screens.details

import androidx.lifecycle.ViewModel
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: WorkoutRepository
) : ViewModel() {
    suspend fun getWorkoutById(workoutId: String): Workout {
        return repository.getWorkoutById(UUID.fromString(workoutId))
    }
}