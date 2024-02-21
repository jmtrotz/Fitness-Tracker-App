package com.jefftrotz.fitnesstracker.data

import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.model.Workout
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

// TODO: Finalize networking
@Singleton
interface API {
    @GET(value = "user")
    suspend fun getUser(): User?

    @POST(value = "")
    suspend fun postUser()

    @GET(value = "workout")
    suspend fun getWorkout(): Workout?

    @POST(value = "")
    suspend fun postWorkout()
}