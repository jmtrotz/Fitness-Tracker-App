package com.jefftrotz.fitnesstracker.ui.screens.account

import com.jefftrotz.fitnesstracker.preferences.Preferences
import com.jefftrotz.fitnesstracker.ui.usecases.user.UserUseCases
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val preferences: Preferences,
    private val useCases: UserUseCases
) : BaseViewModel() {

    fun checkIfAccountExists() {

    }

    fun getUser() {

    }
}