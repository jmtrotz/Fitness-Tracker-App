package com.jefftrotz.fitnesstracker.preferences

interface Preferences {

    fun getStringSet(key: String, defaultValue: Set<String>) : Set<String>

    fun setStringSet(key: String, value: Set<String>)

    fun getString(key: String, defaultValue: String) : String

    fun setString(key: String, value: String)

    fun getFloat(key: String, defaultValue: Float) : Float

    fun setFloat(key: String, value: Float)

    fun getLong(key: String, defaultValue: Long) : Long

    fun setLong(key: String, value: Long)

    fun getInt(key: String, defaultValue: Int) : Int

    fun setInt(key: String, value: Int)

    fun getBoolean(key: String, defaultValue: Boolean) : Boolean

    fun setBoolean(key: String, value: Boolean)
}