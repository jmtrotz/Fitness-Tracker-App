package com.jefftrotz.fitnesstracker.model.states

import com.jefftrotz.fitnesstracker.model.Workout

/**
 * [UIState] for the main screen.
 * @param workouts List of [Workout] objects
 * representing workouts performed by the user.
 * @see Workout
 * @see UIState
 */
data class MainState(val workouts: List<Workout>) : UIState()