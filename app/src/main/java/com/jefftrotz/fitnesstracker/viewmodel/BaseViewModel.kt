package com.jefftrotz.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefftrotz.fitnesstracker.model.states.UIState
import com.jefftrotz.fitnesstracker.model.intents.UserIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    private val _userIntent = MutableStateFlow(UserIntent())

    protected fun setUIState(state: UIState) {
        viewModelScope.launch {
            _uiState.emit(state)
        }
    }

    fun getUIStateFlow(): StateFlow<UIState> {
        return _uiState.asStateFlow()
    }

    fun setUserIntent(intent: UserIntent) {
        viewModelScope.launch {
            _userIntent.emit(intent)
        }
    }

    protected fun getUserIntentFlow(): StateFlow<UserIntent> {
        return _userIntent.asStateFlow()
    }
}