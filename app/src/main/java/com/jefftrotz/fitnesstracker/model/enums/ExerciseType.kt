package com.jefftrotz.fitnesstracker.model.enums

enum class ExerciseType(val typeString: String) {
    CARDIO("Cardio"),
    WEIGHTS("Weights"),
    OTHER("Other");

    override fun toString() : String {
        return when(this) {
            CARDIO -> "Cardio"
            WEIGHTS -> "Weights"
            OTHER -> "Other"
        }
    }

    companion object {

        fun String.exerciseTypeFromString() : ExerciseType {
            return when(this) {
                "Cardio" -> CARDIO
                "Weights" -> WEIGHTS
                else -> OTHER
            }
        }
    }
}