package com.jefftrotz.fitnesstracker.repository

import com.jefftrotz.fitnesstracker.data.API
import com.jefftrotz.fitnesstracker.data.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    @ViewModelScoped
    fun provideRepository(api: API, database: Database): Repository {
        return Repository(api = api, dao = database.getDAO())
    }
}