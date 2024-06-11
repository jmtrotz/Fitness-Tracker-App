package com.jefftrotz.fitnesstracker.ui.usecases.workout

import com.jefftrotz.fitnesstracker.repository.Repository
import com.jefftrotz.fitnesstracker.ui.usecases.user.UserUseCases
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object WorkoutUseCasesModule {

    @Binds
    @ViewModelScoped
    fun provideUseCases(repository: Repository): UserUseCases {
        return UserUseCases(
            getAllWorkouts = GetAllWorkouts(repository),
            addWorkout = AddWorkout(repository),
            updateWorkout = UpdateWorkout(repository),
            deleteWorkout = DeleteWorkout(repository)
        )
    }
}