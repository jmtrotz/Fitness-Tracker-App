package com.jefftrotz.fitnesstracker.data.local.exercise

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.jefftrotz.fitnesstracker.model.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises_table")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises_table WHERE exercise_id = :id")
    suspend fun getExerciseById(id: String): Exercise

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Query("DELETE FROM exercises_table")
    suspend fun deleteAllExercises()
}