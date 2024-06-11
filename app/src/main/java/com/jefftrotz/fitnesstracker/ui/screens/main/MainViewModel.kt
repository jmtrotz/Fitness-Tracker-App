package com.jefftrotz.fitnesstracker.ui.screens.main

import androidx.lifecycle.viewModelScope
import com.jefftrotz.fitnesstracker.model.intents.Logout
import com.jefftrotz.fitnesstracker.model.states.MainState
import com.jefftrotz.fitnesstracker.preferences.Preferences
import com.jefftrotz.fitnesstracker.preferences.PreferenceKeys
import com.jefftrotz.fitnesstracker.ui.usecases.user.UserUseCases
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: Preferences,
    private val useCases: UserUseCases
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            val email = preferences.getString(
                key = PreferenceKeys.KEY_EMAIL,
                defaultValue = PreferenceKeys.DEFAULT_EMAIL
            )

            useCases.getUser.invoke(email = email).collect { user ->
                if (user != null) {
                    val state = MainState(workouts = user.workouts)
                    super.setUIState(state = state)
                }
            }

            super.getUserIntentFlow().collect { intent ->
                when (intent) {
                    is Logout -> logout()
                }
            }
        }
    }

    private fun logout() {
        preferences.setString(key = PreferenceKeys.KEY_EMAIL, value = "")
        preferences.setString(key = PreferenceKeys.KEY_PASSWORD, value = "")
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}