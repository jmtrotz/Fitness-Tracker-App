package com.jefftrotz.fitnesstracker.data.remote.module

import com.jefftrotz.fitnesstracker.data.remote.WorkoutApi
import com.jefftrotz.fitnesstracker.data.remote.UserApi
import com.jefftrotz.fitnesstracker.util.Constants.BASE_URL

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWorkoutApi(): WorkoutApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WorkoutApi::class.java)
    }
}