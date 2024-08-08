package com.jefftrotz.fitnesstracker.data

import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module to provide the [Database] and [DAO] to
 * access data stored locally on the device.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides the [DAO] (data access object).
     * @param database Instance of [Database].
     * @return Instance of [DAO].
     * @see Database
     * @see DAO
     */
    @Provides
    @Singleton
    fun provideDAO(database: Database): DAO {
        return database.getDAO()
    }

    /**
     * Provides the [Database].
     * @param context The application context.
     * @return Instance of [Database].
     * @see Database
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context = context,
            klass = Database::class.java,
            name = "database")
            .fallbackToDestructiveMigration()
            .build()
    }
}