package com.jefftrotz.fitnesstracker.data.local.exercise

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.jefftrotz.fitnesstracker.model.Exercise
import com.jefftrotz.fitnesstracker.util.DateConverter
import com.jefftrotz.fitnesstracker.util.UuidConverter

@Database(entities = [Exercise::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UuidConverter::class)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}