package com.jefftrotz.fitnesstracker.data.remote.module

import com.jefftrotz.fitnesstracker.data.remote.ExerciseApi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    // TODO: Set base URL
    @Provides
    @Singleton
    fun provideExerciseApi(): ExerciseApi {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExerciseApi::class.java)
    }
}