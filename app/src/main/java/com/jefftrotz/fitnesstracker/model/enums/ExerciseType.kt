package com.jefftrotz.fitnesstracker.model.enums

/**
 * Enum to represent the type of exercise that was performed.
 * TODO: Remove hardcoded Strings
 */
enum class ExerciseType {

    WEIGHTS,
    CARDIO,
    OTHER;

    /**
     * Converts an [ExerciseType] to a String.
     * @return String representing the [ExerciseType].
     */
    override fun toString(): String {
        return when(this) {
            WEIGHTS -> "Weights"
            CARDIO -> "Cardio"
            OTHER -> "Other"
        }
    }

    companion object {

        /**
         * Converts a String to an [ExerciseType].
         * @return [ExerciseType] representing the String.
         */
        fun String.exerciseTypeFromString(): ExerciseType {
            return when(this) {
                "Cardio" -> CARDIO
                "Weights" -> WEIGHTS
                else -> OTHER
            }
        }
    }
}