package com.jefftrotz.fitnesstracker.ui.usecases.user

import com.jefftrotz.fitnesstracker.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserUseCasesModule {

    @Binds
    @ViewModelScoped
    fun provideUseCases(repository: Repository): UserUseCases {
        return UserUseCases(
            getAllUsers = GetAllUsers(repository),
            getUser = GetUser(repository),
            addUser = AddUser(repository),
            updateUser = UpdateUser(repository),
            deleteUser = DeleteUser(repository)
        )
    }
}