package com.jefftrotz.fitnesstracker.ui.usecases.workout

data class WorkoutUseCases(
    val getAllWorkouts: GetAllWorkouts,
    val addWorkout: AddWorkout,
    val updateWorkout: UpdateWorkout,
    val deleteWorkout: DeleteWorkout
)