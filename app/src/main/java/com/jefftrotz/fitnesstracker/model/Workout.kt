package com.jefftrotz.fitnesstracker.model

import androidx.room.TypeConverter
import com.jefftrotz.fitnesstracker.model.Exercise.Companion.exerciseListFromCharList
import java.util.Date

data class Workout(
    var name: String,
    val date: Date,
    var description: String = "",
    var exercises: List<Exercise> = ArrayList(emptyList())
) {

    @TypeConverter
    override fun toString(): String {
        return "name: $name, date: ${date.time}, description: $description, exercises: $exercises"
    }

    companion object {

        @TypeConverter
        fun String.workoutFromString(): Workout {
            val name = this.substringAfter("name: ").substringBefore(",")
            val date = this.substringAfter("date: ").substringBefore(",")
            val description = this.substringAfter("description: ").substringBefore(",")
            val exercises = this.substringAfter("exercises: ").substringBefore(",")

            return Workout(
                name = name,
                date = Date(date.toLong()),
                description = description,
                exercises = exercises.toList().exerciseListFromCharList()
            )
        }

        @TypeConverter
        fun List<Char>.workoutListFromCharList(): List<Workout> {
            val workouts = arrayListOf<Workout>()

            for (workoutChar in this) {
                val workout = workoutChar.toString().workoutFromString()
                workouts.add(workout)
            }

            return workouts
        }
    }
}