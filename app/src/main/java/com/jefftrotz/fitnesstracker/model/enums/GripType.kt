package com.jefftrotz.fitnesstracker.model.enums

/**
 * Enum representing the type of grip that
 * was used when performing an exercise.
 * TODO: Remove hardcoded Strings
 */
enum class GripType {

    MIXED_RIGHT_HAND_OVER,
    MIXED_LEFT_HAND_OVER,
    UNDERHAND,
    OVERHAND,
    OTHER;

    /**
     * Converts a [GripType] to a String.
     * @return String representing the [GripType].
     */
    override fun toString() : String {
        return when(this) {
            MIXED_RIGHT_HAND_OVER -> "Mixed, Right Hand Over"
            MIXED_LEFT_HAND_OVER -> "Mixed, Left Hand Over"
            UNDERHAND -> "Underhand"
            OVERHAND -> "Overhand"
            OTHER -> "Other"
        }
    }

    companion object {

        /**
         * Converts a String to a [GripType].
         * @return [GripType] representing the String.
         */
        fun String.gripTypeFromString() : GripType {
            return when(this) {
                "Mixed, Right Hand Over" -> MIXED_RIGHT_HAND_OVER
                "Mixed, Left Hand Over" -> MIXED_LEFT_HAND_OVER
                "Underhand" -> UNDERHAND
                "Overhand" -> OVERHAND
                else -> OTHER
            }
        }
    }
}