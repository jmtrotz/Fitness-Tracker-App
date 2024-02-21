package com.jefftrotz.fitnesstracker.ui.screens.workout

import androidx.lifecycle.viewModelScope
import com.jefftrotz.fitnesstracker.model.intents.CreateWorkout
import com.jefftrotz.fitnesstracker.model.intents.DeleteWorkout
import com.jefftrotz.fitnesstracker.model.intents.RetrieveWorkout
import com.jefftrotz.fitnesstracker.model.intents.UpdateWorkout
import com.jefftrotz.fitnesstracker.repository.Repository
import com.jefftrotz.fitnesstracker.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailsViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    init {
        viewModelScope.launch {
            super.getUserIntentFlow().collect { intent ->
                when (intent) {
                    is CreateWorkout -> repository.insertWorkout(intent.workout)
                    is RetrieveWorkout -> repository.getWorkout(intent.workout)
                    is UpdateWorkout -> repository.updateWorkout(intent.workout)
                    is DeleteWorkout -> repository.deleteWorkout(intent.workout)
                }
            }
        }
    }
}