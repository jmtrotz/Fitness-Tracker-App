package com.jefftrotz.fitnesstracker.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.model.states.ErrorState
import com.jefftrotz.fitnesstracker.model.states.LoadingState
import com.jefftrotz.fitnesstracker.model.states.LoginState
import com.jefftrotz.fitnesstracker.model.intents.Login
import com.jefftrotz.fitnesstracker.model.intents.UpdateState
import com.jefftrotz.fitnesstracker.preferences.PreferenceKeys
import com.jefftrotz.fitnesstracker.preferences.Preferences
import com.jefftrotz.fitnesstracker.ui.usecases.UseCases
import com.jefftrotz.fitnesstracker.util.login.LoginUtils
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel
import com.jefftrotz.fitnesstracker.viewmodel.StringProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val preferences: Preferences,
    private val useCase: UseCases
) : BaseViewModel() {

    private var state = LoginState()

    init {
        viewModelScope.launch {
            super.getUserIntentFlow().collect { intent ->
                when (intent) {
                    is UpdateState -> {
                        state = intent.newState as LoginState
                        super.setUIState(state)
                    }
                    is Login -> verifyInput()
                }
            }
        }
    }

    private suspend fun verifyInput() {
        super.setUIState(LoadingState())
        state.emailState.errorMessage = ""
        state.emailState.isError = false
        state.passwordState.errorMessage = ""
        state.passwordState.isError = false

        val email = state.emailState.text
        val password = state.passwordState.text
        val isEmailValid = LoginUtils.isEmailAddressValid(email)
        val isPasswordValid = LoginUtils.isPasswordValid(password)

        var stringID = -1
        var errorMessage = ""

        // Make sure input is valid
        if (!isEmailValid) {
            state.emailState.isError = true
            stringID = R.string.error_email_blank
            errorMessage = stringProvider.getString(id = stringID)
        } else if (!isPasswordValid) {
            state.passwordState.isError = true
            stringID = R.string.error_invalid_password
            errorMessage = stringProvider.getString(id = stringID)
        }

        if (stringID != -1 && errorMessage.isNotBlank()) {
            super.setUIState(ErrorState(message = errorMessage))
            return
        }

        checkIfUserExists(email = email)
    }

    private suspend fun checkIfUserExists(email: String) {
        useCase.getUser.invoke(email = email).collect { user ->
            if (user != null) {
                login(user = user);
            } else {
                val stringID = R.string.error_account_doesnt_exist
                val errorMessage = stringProvider.getString(id = stringID)
                super.setUIState(ErrorState(message = errorMessage))
            }
        }
    }

    private fun login(user: User) {
        val savedEmail = preferences.getString(
            PreferenceKeys.KEY_EMAIL,
            PreferenceKeys.DEFAULT_EMAIL
        )
        val savedPassword = preferences.getString(
            PreferenceKeys.KEY_PASSWORD,
            PreferenceKeys.DEFAULT_PASSWORD
        )

        if (user.email != savedEmail) {
            val stringID = R.string.error_incorrect_credentials
            val errorMessage = stringProvider.getString(id = stringID)
            super.setUIState(ErrorState(message = errorMessage))
        }

        val passwordIsCorrect = LoginUtils.verifyPassword(
            savedPassword,
            user.password,
            user.passwordSalt
        )

        if (passwordIsCorrect) {
            state.loginSuccessful = true
            super.setUIState(state)
        } else {
            val stringID = R.string.error_incorrect_credentials
            val errorMessage = stringProvider.getString(id = stringID)
            super.setUIState(ErrorState(message = errorMessage))
        }
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}