package com.jefftrotz.fitnesstracker.screens.workoutDetails

import androidx.lifecycle.ViewModel
import com.jefftrotz.fitnesstracker.data.DataWrapper
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailsViewModel @Inject constructor(
    private val repository: WorkoutRepository
) : ViewModel() {
    suspend fun getWorkoutById(id: UUID): DataWrapper<Workout> {
        return repository.getWorkoutById(id)
    }
}