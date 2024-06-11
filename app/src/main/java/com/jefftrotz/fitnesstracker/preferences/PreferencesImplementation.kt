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
        Log.d(TAG, "Getting $key")
        return prefs.getStringSet(key, defaultValue) ?: defaultValue
    }

    override fun setStringSet(key: String, value: Set<String>) {
        val currentValue = getStringSet(key, value)
        if (value != currentValue) {
            Log.d(TAG, "Saving $key wth value $value")
            prefs.edit()
                .putStringSet(key, value)
                .apply()
        }
    }

    override fun getString(key: String, defaultValue: String): String {
        Log.d(TAG, "Getting $key")
        return prefs.getString(key, defaultValue) ?: defaultValue
    }

    override fun setString(key: String, value: String) {
        val currentValue = getString(key, value)
        if (value != currentValue) {
            Log.d(TAG, "Saving $key wth value $value")
            prefs.edit()
                .putString(key, value)
                .apply()
        }
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        Log.d(TAG, "Getting $key")
        return prefs.getFloat(key, defaultValue)
    }

    override fun setFloat(key: String, value: Float) {
        val currentValue = getFloat(key, value)
        if (value != currentValue) {
            Log.d(TAG, "Saving $key wth value $value")
            prefs.edit()
                .putFloat(key, value)
                .apply()
        }
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        Log.d(TAG, "Getting $key")
        return prefs.getLong(key, defaultValue)
    }

    override fun setLong(key: String, value: Long) {
        val currentValue = getLong(key, value)
        if (value != currentValue) {
            Log.d(TAG, "Saving $key wth value $value")
            prefs.edit()
                .putLong(key, value)
                .apply()
        }
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        Log.d(TAG, "Getting $key")
        return prefs.getInt(key, defaultValue)
    }

    override fun setInt(key: String, value: Int) {
        val currentValue = getInt(key, value)
        if (value != currentValue) {
            Log.d(TAG, "Saving $key wth value $value")
            prefs.edit()
                .putInt(key, value)
                .apply()
        }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        Log.d(TAG, "Getting $key")
        return prefs.getBoolean(key, defaultValue)
    }

    override fun setBoolean(key: String, value: Boolean) {
        val currentValue = getBoolean(key, value)
        if (value != currentValue) {
            Log.d(TAG, "Saving $key wth value $value")
            prefs.edit()
                .putBoolean(key, value)
                .apply()
        }
    }

    companion object {
        const val TAG = "Preferences"
    }
}