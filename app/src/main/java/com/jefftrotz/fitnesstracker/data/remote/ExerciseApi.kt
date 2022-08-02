package com.jefftrotz.fitnesstracker.data.remote

import com.jefftrotz.fitnesstracker.model.Exercise
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ExerciseApi {
    // TODO: Set up URL/queries
    @GET(value = "")
    suspend fun getExercises(): List<Exercise>
}