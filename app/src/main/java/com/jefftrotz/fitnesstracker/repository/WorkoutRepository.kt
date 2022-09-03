package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.local.exercise.WorkoutDao
import com.jefftrotz.fitnesstracker.model.Workout

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WorkoutRepository @Inject constructor(private val workoutDao: WorkoutDao) {
    fun getAllExercises() {
        workoutDao.getAllWorkouts().flowOn(Dispatchers.IO)
    }

    suspend fun getWorkoutById(id: String){
        workoutDao.getWorkoutById(id)
    }

    suspend fun insertWorkout(workout: Workout){
        workoutDao.insertWorkout(workout)
    }

    suspend fun updateWorkout(workout: Workout){
        workoutDao.updateWorkout(workout)
    }

    suspend fun deleteWorkout(workout: Workout){
        workoutDao.deleteWorkout(workout)
    }

    suspend fun deleteAllWorkouts(){
        workoutDao.deleteAllWorkouts()
    }
}