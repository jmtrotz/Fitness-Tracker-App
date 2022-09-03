package com.jefftrotz.fitnesstracker.data.local.module

import android.content.Context
import androidx.room.Room

import com.jefftrotz.fitnesstracker.data.local.exercise.WorkoutDatabase
import com.jefftrotz.fitnesstracker.data.local.exercise.WorkoutDao
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
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWorkoutDao(workoutDatabase: WorkoutDatabase): WorkoutDao {
        return workoutDatabase.workoutDao()
    }

    @Singleton
    @Provides
    fun provideExerciseDatabase(@ApplicationContext context: Context): WorkoutDatabase {
        return Room.databaseBuilder(context, WorkoutDatabase::class.java, "workout_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}