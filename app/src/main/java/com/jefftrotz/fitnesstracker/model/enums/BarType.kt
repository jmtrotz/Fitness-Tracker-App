package com.jefftrotz.fitnesstracker.model.enums

/**
 * Enum to represent the type of bar that was used for an exercise.
 * TODO: Remove hardcoded Strings
 */
enum class BarType {

    SUPER_CURL,
    DUMBBELL,
    BARBELL,
    EZ_CURL,
    OTHER;

    /**
     * Converts a [BarType] to a String.
     * @return String representing the [BarType].
     */
    override fun toString(): String {
        return when(this) {
            SUPER_CURL -> "Super Curl"
            DUMBBELL -> "Dumbbell"
            BARBELL -> "Barbell"
            EZ_CURL -> "EZ Curl"
            OTHER -> "Other"
        }
    }

    companion object {

        /**
         * Converts a String to a [BarType].
         * @return [BarType] representing the String.
         */
        fun String.barTypeFromString(): BarType {
            return when(this) {
                "Super Curl" -> SUPER_CURL
                "Dumbbell" -> DUMBBELL
                "Barbell" -> BARBELL
                "EZ Curl" -> EZ_CURL
                else -> OTHER
            }
        }
    }
}