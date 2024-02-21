package com.jefftrotz.fitnesstracker.model.states

import com.jefftrotz.fitnesstracker.model.Workout

class MainState(val workouts: List<Workout>) : UIState()