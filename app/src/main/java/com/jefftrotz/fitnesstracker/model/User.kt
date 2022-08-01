package com.jefftrotz.fitnesstracker.model

import java.util.UUID

data class User(
    val id: UUID,
    val name: String,
    val password: String
)