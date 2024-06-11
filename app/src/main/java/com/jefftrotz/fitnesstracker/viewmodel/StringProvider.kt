package com.jefftrotz.fitnesstracker.viewmodel

import android.content.Context

class StringProvider(private val context: Context) {

    fun getString(id: Int): String {
        return context.getString(id)
    }
}