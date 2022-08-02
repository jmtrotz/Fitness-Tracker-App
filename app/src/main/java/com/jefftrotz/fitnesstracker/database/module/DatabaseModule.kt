package com.jefftrotz.fitnesstracker.database.module

import android.content.Context
import androidx.room.Room

import com.jefftrotz.fitnesstracker.database.ExerciseDatabase
import com.jefftrotz.fitnesstracker.database.ExerciseDatabaseDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideExercisesDao(exerciseDatabase: ExerciseDatabase): ExerciseDatabaseDao =
        exerciseDatabase.exerciseDao()

    @Singleton
    @Provides
    fun provideExerciseDatabase(@ApplicationContext context: Context): ExerciseDatabase =
        Room.databaseBuilder(context, ExerciseDatabase::class.java, "exercises_database")
        .fallbackToDestructiveMigration()
        .build()
}