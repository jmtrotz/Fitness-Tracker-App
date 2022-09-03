package com.jefftrotz.fitnesstracker.data.local.workout

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.util.converters.DateConverter
import com.jefftrotz.fitnesstracker.util.converters.ListConverter
import com.jefftrotz.fitnesstracker.util.converters.UuidConverter

@Database(entities = [Workout::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UuidConverter::class, ListConverter::class)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}