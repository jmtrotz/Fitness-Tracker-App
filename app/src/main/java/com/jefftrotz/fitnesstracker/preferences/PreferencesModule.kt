package com.jefftrotz.fitnesstracker.preferences

import android.content.Context
import com.jefftrotz.fitnesstracker.preferences.Preferences
import com.jefftrotz.fitnesstracker.preferences.PreferencesImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    @ViewModelScoped
    fun providePreferences(@ApplicationContext context: Context) : Preferences {
        return PreferencesImplementation(context = context)
    }
}