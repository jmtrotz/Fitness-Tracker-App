package com.jefftrotz.fitnesstracker.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PreferencesImplementation(context: Context) : Preferences {
    private var prefs: SharedPreferences

    init {
        Log.d(TAG, "Initializing")
        prefs = context.getSharedPreferences("workout_prefs", Context.MODE_PRIVATE)
    }

    override fun getStringSet(key: String, defaultValue: Set<String>): Set<String> {
        return prefs.getStringSet(key, defaultValue) ?: defaultValue
    }

    override fun setStringSet(key: String, value: Set<String>) {
        val currentValue = getStringSet(key, value)
        if (value != currentValue) {
            prefs
        }
    }

    override fun getString(key: String, defaultValue: String): String {
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    override fun setString(key: String, value: String) {
        val currentValue = getString(key, value)
        if (value != currentValue) {
            prefs
        }
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return prefs.getFloat(key, defaultValue)
    }

    override fun setFloat(key: String, value: Float) {
        val currentValue = getFloat(key, value)
        if (value != currentValue) {
            prefs
        }
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return prefs.getLong(key, defaultValue)
    }

    override fun setLong(key: String, value: Long) {
        val currentValue = getLong(key, value)
        if (value != currentValue) {
            prefs
        }
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return prefs.getInt(key, defaultValue)
    }

    override fun setInt(key: String, value: Int) {
        val currentValue = getInt(key, value)
        if (value != currentValue) {
            prefs
        }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    override fun setBoolean(key: String, value: Boolean) {
        val currentValue = getBoolean(key, value)
        if (value != currentValue) {
            prefs
        }
    }

    companion object {
        var TAG = PreferencesImplementation::class.simpleName
    }
}