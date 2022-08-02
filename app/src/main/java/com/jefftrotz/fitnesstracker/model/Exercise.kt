package com.jefftrotz.fitnesstracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "exercises_table")
data class Exercise(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "exercise_date")
    val date: Date,

    @ColumnInfo(name = "exercise_name")
    val name: String,

    @ColumnInfo(name = "exercise_type")
    val exerciseType: ExerciseType,

    @ColumnInfo(name = "exercise_distance")
    val distance: Double = 0.0,

    @ColumnInfo(name = "exercise_weight_used")
    val weightUsed: Double = 0.0,

    @ColumnInfo(name = "exercise_reps_done")
    val repsDone: Int = 0
)