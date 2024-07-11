package com.jefftrotz.fitnesstracker.model.actions

import com.jefftrotz.fitnesstracker.model.Workout

/**
 * [UserAction] to create a new [Workout].
 * @param workout [Workout] object representing the workout to create.
 * @see UserAction
 * @see Workout
 */
class CreateWorkout(val workout: Workout) : UserAction()