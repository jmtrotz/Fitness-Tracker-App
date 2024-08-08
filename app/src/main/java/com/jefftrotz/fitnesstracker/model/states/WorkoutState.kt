package com.jefftrotz.fitnesstracker.model.states

import com.jefftrotz.fitnesstracker.model.Workout

/**
 * [UIState] for the create and edit workout screens.
 * @param workout [Workout] object representing the workout being viewed by a user.
 * @see Workout
 * @see UIState
 */
class WorkoutState(val workout: Workout? = null): UIState()