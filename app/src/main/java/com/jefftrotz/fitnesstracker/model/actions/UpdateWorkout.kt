package com.jefftrotz.fitnesstracker.model.actions

import com.jefftrotz.fitnesstracker.model.Workout

/**
 * [UserAction] to update a [Workout].
 * @param workout [Workout] object representing the workout to update.
 * @see UserAction
 * @see Workout
 */
class UpdateWorkout(val workout: Workout): UserAction()