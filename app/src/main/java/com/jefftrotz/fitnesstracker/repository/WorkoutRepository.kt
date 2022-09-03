package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.local.workout.WorkoutDao
import com.jefftrotz.fitnesstracker.data.remote.WorkoutApi
import com.jefftrotz.fitnesstracker.model.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class WorkoutRepository @Inject constructor(
    private val workoutDao: WorkoutDao,
    private val workoutApi: WorkoutApi
) {
    fun getAllWorkouts(): Flow<List<Workout>> {
        return workoutDao.getAllWorkouts().flowOn(Dispatchers.IO)
    }

    suspend fun getWorkoutById(id: UUID): Workout {
        return workoutDao.getWorkoutById(id)
    }

    suspend fun insertWorkout(workout: Workout) {
        workoutDao.insertWorkout(workout)
    }

    suspend fun updateWorkout(workout: Workout) {
        workoutDao.updateWorkout(workout)
    }

    suspend fun deleteWorkout(workout: Workout) {
        workoutDao.deleteWorkout(workout)
    }

    suspend fun deleteAllWorkouts() {
        workoutDao.deleteAllWorkouts()
    }

    // TODO: Finalize networking
    suspend fun getWorkout(workout: Workout): Workout {
        return workoutApi.getWorkout()
    }

    // TODO: Finalize networking
    suspend fun postWorkout(workout: Workout) {
        workoutApi.postWorkout()
    }
}