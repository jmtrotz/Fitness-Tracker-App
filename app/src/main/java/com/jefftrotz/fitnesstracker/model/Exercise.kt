package com.jefftrotz.fitnesstracker.model

data class Exercise(
    val name: String,
    val type: ExerciseType,
    val description: String = "",
    val distance: Double = 0.0,
    val weight: Double = 0.0,
    val repetitions: Int = 0
)