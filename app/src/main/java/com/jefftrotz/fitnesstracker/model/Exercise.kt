package com.jefftrotz.fitnesstracker.model

data class Exercise(
    val name: String,
    val type: ExerciseType,
    val distance: Double = 0.0,
    val weightUsed: Double = 0.0,
    val repsDone: Int = 0
)