package com.jefftrotz.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jefftrotz.fitnesstracker.model.states.UIState
import com.jefftrotz.fitnesstracker.model.actions.UserAction

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    private val _userAction = MutableStateFlow(UserAction())
    private val _uiState = MutableStateFlow(UIState())
    private val _sideEffect = MutableStateFlow("")

    fun getUIStateFlow(): StateFlow<UIState> {
        return _uiState.asStateFlow()
    }

    protected fun setUIState(state: UIState) {
        viewModelScope.launch {
            _uiState.emit(state)
        }
    }

    protected fun getUserActionFlow(): StateFlow<UserAction> {
        return _userAction.asStateFlow()
    }

    fun setUserAction(action: UserAction) {
        viewModelScope.launch {
            _userAction.emit(action)
        }
    }

    fun getSideEffectFlow(): StateFlow<String> {
        return _sideEffect.asStateFlow()
    }

    protected fun setSideEffect(message: String) {
        viewModelScope.launch {
            _sideEffect.emit(message)
        }
    }
}