package com.jefftrotz.fitnesstracker.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefftrotz.fitnesstracker.model.Workout
import com.jefftrotz.fitnesstracker.repository.WorkoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WorkoutRepository) : ViewModel() {
    private val _workoutList = MutableStateFlow<List<Workout>>(emptyList())
    val workoutList = _workoutList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllWorkouts().collect { workoutList ->
                if (workoutList.isEmpty()) {
                    Log.d(TAG, "Workout list is empty")
                } else {
                    _workoutList.value = workoutList
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}