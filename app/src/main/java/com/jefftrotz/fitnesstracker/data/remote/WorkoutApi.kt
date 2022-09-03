package com.jefftrotz.fitnesstracker.data.remote

import com.jefftrotz.fitnesstracker.model.Workout
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

// TODO: Finalize networking
@Singleton
interface WorkoutApi {
    @GET(value = "workout")
    suspend fun getWorkout(): Workout

    @POST(value = "")
    suspend fun postWorkout()
}