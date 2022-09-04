package com.jefftrotz.fitnesstracker.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("EEEE, MMMM d yyyy", Locale.getDefault())
    return format.format(date)
}