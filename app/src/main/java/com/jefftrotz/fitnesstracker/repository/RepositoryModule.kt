package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.API
import com.jefftrotz.fitnesstracker.data.Database
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Binds
    @Singleton
    fun provideRepository(api: API, database: Database): Repository {
        return RepositoryImplementation(api = api, dao = database.getDAO())
    }
}