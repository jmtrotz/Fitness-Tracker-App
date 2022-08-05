package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.local.exercise.ExerciseDao
import com.jefftrotz.fitnesstracker.model.Exercise
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) {
    fun getAllExercises() = exerciseDao.getAllExercises()
    suspend fun getExerciseById(id: String) = exerciseDao.getExerciseById(id = id)
    suspend fun insertExercise(exercise: Exercise) = exerciseDao.insertExercise(exercise = exercise)
    suspend fun updateExercise(exercise: Exercise) = exerciseDao.updateExercise(exercise = exercise)
    suspend fun deleteExercise(exercise: Exercise) = exerciseDao.deleteExercise(exercise = exercise)
    suspend fun deleteAllExercises() = exerciseDao.deleteAllExercises()
}