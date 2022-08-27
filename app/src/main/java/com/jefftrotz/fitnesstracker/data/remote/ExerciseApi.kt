package com.jefftrotz.fitnesstracker.data.remote

import com.jefftrotz.fitnesstracker.model.Exercise
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

// TODO: Finish GET and POST requests
@Singleton
interface ExerciseApi {
    @GET(value = "exercise")
    suspend fun getExercises(): List<Exercise>

    @POST(value = "")
    suspend fun postExercises()
}