package com.jefftrotz.fitnesstracker.data.local.module

import android.content.Context
import androidx.room.Room

import com.jefftrotz.fitnesstracker.data.local.exercise.ExerciseDatabase
import com.jefftrotz.fitnesstracker.data.local.exercise.ExerciseDao
import com.jefftrotz.fitnesstracker.data.local.user.UserDao
import com.jefftrotz.fitnesstracker.data.local.user.UserDatabase

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
    fun provideUserDao(userDatabase: UserDatabase): UserDao = userDatabase.userDao()

    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideExerciseDao(exerciseDatabase: ExerciseDatabase): ExerciseDao =
        exerciseDatabase.exerciseDao()

    @Singleton
    @Provides
    fun provideExerciseDatabase(@ApplicationContext context: Context): ExerciseDatabase =
        Room.databaseBuilder(context, ExerciseDatabase::class.java, "exercises_database")
        .fallbackToDestructiveMigration()
        .build()
}