package com.jefftrotz.fitnesstracker.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.jefftrotz.fitnesstracker.model.Exercise
import com.jefftrotz.fitnesstracker.repository.ExerciseRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExerciseViewModel @Inject constructor(
    private val repository:ExerciseRepository
) : ViewModel() {
    private val _exerciseList = MutableStateFlow<List<Exercise>>(emptyList())
    val exerciseList = _exerciseList.asStateFlow()

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            repository.getAllExercises().distinctUntilChanged().collect { exerciseList ->
                if (exerciseList.isNullOrEmpty()) {
                    Log.e(TAG, "Exercise list was null or empty")
                } else {
                    _exerciseList.value = exerciseList
                }
            }
        }
    }

    fun insertExercise(exercise: Exercise) = viewModelScope.launch {
        repository.insertExercise(exercise = exercise)
    }

    fun updateExercise(exercise: Exercise) = viewModelScope.launch {
        repository.updateExercise(exercise = exercise)
    }

    fun deleteExercise(exercise: Exercise) = viewModelScope.launch {
        repository.deleteExercise(exercise = exercise)
    }

    fun deleteAllExercises() = viewModelScope.launch {
        repository.deleteAllExercises()
    }

    companion object {
        private const val TAG = "ExerciseViewModel"
    }
}