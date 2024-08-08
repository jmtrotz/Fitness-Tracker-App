package com.jefftrotz.fitnesstracker.model

import androidx.room.TypeConverter
import com.jefftrotz.fitnesstracker.model.enums.ExerciseType
import com.jefftrotz.fitnesstracker.model.enums.ExerciseType.Companion.exerciseTypeFromString

data class Exercise(
    val name: String,
    val type: ExerciseType,
    val description: String = "",
    val distance: Double = 0.0,
    val weight: Double = 0.0,
    val repetitions: Int = 0
) {

    @TypeConverter
    override fun toString(): String {
        return "name: $name," +
                "type: $type," +
                "description: $description," +
                "distance: $distance," +
                "weight: $weight," +
                "repetitions: $repetitions"
    }

    companion object {

        @TypeConverter
        fun String.exerciseFromString(): Exercise {
            val name = this.substringAfter("name: ").substringBefore(",")
            val type = this.substringAfter("type: ").substringBefore(",")
            val description = substringAfter("description: ").substringAfter(",")
            val distance = substringAfter("distance: ").substringAfter(",")
            val weight = substringAfter("weight: ").substringBefore(",")
            val repetitions = substringAfter("repetitions: ").substringBefore(",")

            return Exercise(
                name = name,
                type = type.exerciseTypeFromString(),
                description = description,
                distance = distance.toDouble(),
                weight = weight.toDouble(),
                repetitions.toInt()
            )
        }

        @TypeConverter
        fun List<Char>.exerciseListFromCharList(): List<Exercise> {
            val exercises = arrayListOf<Exercise>()

            for (exerciseChar in this) {
                val exercise = exerciseChar.toString().exerciseFromString()
                exercises.add(exercise)
            }

            return exercises
        }
    }
}