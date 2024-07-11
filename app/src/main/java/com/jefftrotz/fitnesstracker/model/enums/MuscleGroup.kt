package com.jefftrotz.fitnesstracker.model.enums

/**
 * Enum representing the muscle group that
 * was worked when performing an exercise.
 * TODO: Remove hardcoded Strings
 */
enum class MuscleGroup {

    SHOULDERS,
    CHEST,
    OTHER,
    ARMS,
    BACK,
    LEGS;

    /**
     * Converts a [MuscleGroup] to a String.
     * @return String representing the [MuscleGroup].
     */
    override fun toString() : String {
        return when(this) {
            SHOULDERS -> "Shoulders"
            CHEST -> "Chest"
            OTHER -> "Other"
            ARMS -> "Arms"
            BACK -> "Back"
            LEGS -> "Legs"
        }
    }

    companion object {

        /**
         * Converts a String to a [MuscleGroup].
         * @return [MuscleGroup] representing the String.
         */
        fun String.MuscleGroupFromString() : MuscleGroup {
            return when(this) {
                "Shoulders" -> SHOULDERS
                "Chest" -> CHEST
                "Arms" -> ARMS
                "Back" -> BACK
                "Legs" -> LEGS
                else -> OTHER
            }
        }
    }
}