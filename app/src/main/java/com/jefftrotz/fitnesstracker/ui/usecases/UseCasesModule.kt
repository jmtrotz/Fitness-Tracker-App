package com.jefftrotz.fitnesstracker.ui.usecases

import com.jefftrotz.fitnesstracker.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Binds
    @ViewModelScoped
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            getAllUsers = GetAllUsers(repository),
            getUser = GetUser(repository),
            addUser = AddUser(repository),
            updateUser = UpdateUser(repository),
            deleteUser = DeleteUser(repository)
        )
    }
}