package com.jefftrotz.fitnesstracker.ui.screens.main

import androidx.lifecycle.viewModelScope
import com.jefftrotz.fitnesstracker.model.intents.Logout
import com.jefftrotz.fitnesstracker.model.states.MainState
import com.jefftrotz.fitnesstracker.preferences.Preferences
import com.jefftrotz.fitnesstracker.preferences.PreferenceKeys
import com.jefftrotz.fitnesstracker.ui.usecases.UseCases
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferences: Preferences,
    private val useCases: UseCases
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            val email = preferences.getString(
                PreferenceKeys.KEY_EMAIL,
                PreferenceKeys.DEFAULT_EMAIL
            )

            useCases.getUser.invoke(email = email).collect { user ->
                if (user != null) {
                    super.setUIState(MainState(user.workouts))
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

    }

    companion object {
        const val TAG = "MainViewModel"
    }
}