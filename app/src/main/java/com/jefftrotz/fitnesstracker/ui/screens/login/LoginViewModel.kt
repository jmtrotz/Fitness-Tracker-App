package com.jefftrotz.fitnesstracker.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.jefftrotz.fitnesstracker.R
import com.jefftrotz.fitnesstracker.model.User
import com.jefftrotz.fitnesstracker.model.actions.Cancel
import com.jefftrotz.fitnesstracker.model.states.ErrorState
import com.jefftrotz.fitnesstracker.model.states.LoadingState
import com.jefftrotz.fitnesstracker.model.states.LoginState
import com.jefftrotz.fitnesstracker.model.actions.Login
import com.jefftrotz.fitnesstracker.model.actions.UpdateState

import com.jefftrotz.fitnesstracker.preferences.PreferenceKeys
import com.jefftrotz.fitnesstracker.preferences.Preferences
import com.jefftrotz.fitnesstracker.ui.usecases.user.UserUseCases

import com.jefftrotz.fitnesstracker.util.LoginUtils
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel
import com.jefftrotz.fitnesstracker.viewmodel.StringProvider

import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val preferences: Preferences,
    private val useCase: UserUseCases
) : BaseViewModel() {

    private var state = LoginState()

    init {
        viewModelScope.launch {
            super.getUserActionFlow().collect { intent ->
                when (intent) {
                    is UpdateState -> {
                        state = intent.newState as LoginState
                        super.setUIState(state)
                    }
                    is Login -> verifyInput()
                    is Cancel -> cancelLogin()
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
            super.setSideEffect(message = errorMessage)
            super.setUIState(ErrorState())
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
                super.setSideEffect(message = errorMessage)
                super.setUIState(ErrorState())
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
            super.setSideEffect(message = errorMessage)
            super.setUIState(ErrorState())
        }

        val passwordIsCorrect = LoginUtils.verifyPassword(
            savedPassword,
            user.password,
            user.passwordSalt
        )

        if (passwordIsCorrect) {
            state.isLoginSuccessful = true
            super.setUIState(state)
        } else {
            val stringID = R.string.error_incorrect_credentials
            val errorMessage = stringProvider.getString(id = stringID)
            super.setSideEffect(message = errorMessage)
            super.setUIState(ErrorState())
        }
    }

    private fun cancelLogin() {
        val emailHint = R.string.placeholder_email
        state.emailState.hint = stringProvider.getString(emailHint)
        state.emailState.text = ""
        state.emailState.errorMessage = ""
        state.emailState.isHintVisible = true
        state.emailState.isError = false

        val passwordHint = R.string.placeholder_email
        state.passwordState.hint = stringProvider.getString(passwordHint)
        state.passwordState.text = ""
        state.passwordState.errorMessage = ""
        state.passwordState.isHintVisible = true
        state.passwordState.isError = false
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}