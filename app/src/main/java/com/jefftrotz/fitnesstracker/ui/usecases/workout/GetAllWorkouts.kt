package com.jefftrotz.fitnesstracker.ui.usecases.workout

import android.util.Log
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllWorkouts(private val repository: Repository) {

//    suspend operator fun invoke(): Flow<List<Workout>> {
//        Log.d(TAG, "Getting all workouts")
//        //return repository.getAllWorkouts()
//    }

    companion object {
        const val TAG = "GetAllWorkouts"
    }
}