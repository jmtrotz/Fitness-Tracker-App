package com.jefftrotz.fitnesstracker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.jefftrotz.fitnesstracker.model.Exercise
import com.jefftrotz.fitnesstracker.model.ExerciseType

import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ExerciseDatabaseDao {
    @Query("SELECT * FROM exercises_table WHERE id = :id")
    suspend fun getExerciseById(id: String): Exercise

    @Query("SELECT * FROM exercises_table WHERE exercise_type = :exerciseType")
    suspend fun getExerciseByType(exerciseType: ExerciseType): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises_table WHERE exercise_date = :date")
    suspend fun getExerciseByDate(date: Date): Flow<List<Exercise>>

    @Query("SELECT * FROM exercises_table")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("DELETE FROM exercises_table")
    suspend fun deleteAllExercises()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)
}