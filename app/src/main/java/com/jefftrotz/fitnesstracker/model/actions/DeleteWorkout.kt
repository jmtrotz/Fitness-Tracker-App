package com.jefftrotz.fitnesstracker.model.actions

import com.jefftrotz.fitnesstracker.model.Workout

/**
 * [UserAction] to delete a [Workout].
 * @param workout [Workout] object representing the workout to delete.
 * @see UserAction
 * @see Workout
 */
class DeleteWorkout(val workout: Workout) : UserAction()