package com.jefftrotz.fitnesstracker.data.local.exercise

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.jefftrotz.fitnesstracker.model.Workout
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout_table")
    fun getAllWorkouts(): Flow<List<Workout>>

    @Query("SELECT * FROM workout_table WHERE workout_id = :id")
    suspend fun getWorkoutById(id: String): Workout

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWorkout(workout: Workout)

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Query("DELETE FROM workout_table")
    suspend fun deleteAllWorkouts()
}