package com.jefftrotz.fitnesstracker.screens.main

import androidx.lifecycle.ViewModel
import com.jefftrotz.fitnesstracker.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(repository: ExerciseRepository) : ViewModel() {
}