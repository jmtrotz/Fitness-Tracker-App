package com.jefftrotz.fitnesstracker.ui.usecases.workout

data class WorkoutUseCases(
    val getAllWorkouts: GetAllWorkouts,
    val updateWorkout: UpdateWorkout,
    val deleteWorkout: DeleteWorkout,
    val addWorkout: AddWorkout
)