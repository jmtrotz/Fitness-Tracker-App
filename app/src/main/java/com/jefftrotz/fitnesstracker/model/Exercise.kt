package com.jefftrotz.fitnesstracker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.Date
import java.util.UUID

@Entity(tableName = "exercises_table")
data class Exercise(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "exercise_id")
    val id: UUID = UUID.randomUUID(),

    @NonNull
    @ColumnInfo(name = "exercise_date")
    val date: Date,

    @NonNull
    @ColumnInfo(name = "exercise_type")
    val type: ExerciseType,

    @ColumnInfo(name = "exercise_description")
    val description: String,

    @ColumnInfo(name = "exercise_distance")
    val distance: Double = 0.0,

    @ColumnInfo(name = "exercise_weight")
    val weight: Double = 0.0,

    @ColumnInfo(name = "exercise_repetitions")
    val repetitions: Int = 0
)