package com.jefftrotz.fitnesstracker.data

import com.jefftrotz.fitnesstracker.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Module to provide the [API] to communicate with the server.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Builds the [API] using Retrofit.
     * @return Instance of [API].
     * @see API
     */
    @Provides
    @Singleton
    fun provideAPI(): API {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(API::class.java)
    }
}