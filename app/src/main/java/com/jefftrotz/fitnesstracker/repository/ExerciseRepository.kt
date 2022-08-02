package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.local.ExerciseDatabaseDao
import com.jefftrotz.fitnesstracker.model.Exercise
import com.jefftrotz.fitnesstracker.model.ExerciseType

import java.util.Date
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDatabaseDao: ExerciseDatabaseDao
) {
    suspend fun getExerciseById(id: String) =
        exerciseDatabaseDao.getExerciseById(id = id)

    suspend fun getExerciseByType(exerciseType: ExerciseType) =
        exerciseDatabaseDao.getExerciseByType(exerciseType = exerciseType)

    suspend fun getExerciseByDate(date: Date) =
        exerciseDatabaseDao.getExerciseByDate(date = date)

    suspend fun getAllExercises() =
        exerciseDatabaseDao.getAllExercises()

    suspend fun deleteAllExercises() =
        exerciseDatabaseDao.deleteAllExercises()

    suspend fun insertExercise(exercise: Exercise) =
        exerciseDatabaseDao.insertExercise(exercise = exercise)

    suspend fun updateExercise(exercise: Exercise) =
        exerciseDatabaseDao.updateExercise(exercise = exercise)

    suspend fun deleteExercise(exercise: Exercise) =
        exerciseDatabaseDao.deleteExercise(exercise = exercise)
}