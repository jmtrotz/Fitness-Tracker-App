package com.jefftrotz.fitnesstracker.model.states

data class TextFieldState(
    var text: String = "",
    var hint: String = "",
    var errorMessage: String = "",
    var isHintVisible: Boolean = true,
    var isError: Boolean = false
)