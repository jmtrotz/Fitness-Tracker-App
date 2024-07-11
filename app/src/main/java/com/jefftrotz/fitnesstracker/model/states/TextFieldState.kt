package com.jefftrotz.fitnesstracker.model.states

/**
 * [UIState] for a text field.
 * @param isHintVisible Boolean representing whether the hint is visible or not.
 * @param errorMessage String representing an error message to show below the text field.
 * @param isError Boolean representing whether the text field is in an error state or not.
 * @param text String representing text that was entered into the text field by the user.
 * @param hint String representing the hint to show in an empty text field.
 * @see UIState
 */
data class TextFieldState(
    var isHintVisible: Boolean = true,
    var errorMessage: String = "",
    var isError: Boolean = false,
    var text: String = "",
    var hint: String = ""
)