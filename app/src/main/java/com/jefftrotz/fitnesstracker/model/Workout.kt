package com.jefftrotz.fitnesstracker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "workout_table")
data class Workout(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "workout_id")
    val id: UUID = UUID.randomUUID(),

    @NonNull
    @ColumnInfo(name = "workout_date")
    val date: Date,

    @NonNull
    @ColumnInfo(name = "workout_name")
    val name: String,

    @ColumnInfo(name = "workout_description")
    val description: String = "",

    @ColumnInfo(name = "workout_exercise_list")
    val exerciseList: List<Exercise> = ArrayList(emptyList())
)