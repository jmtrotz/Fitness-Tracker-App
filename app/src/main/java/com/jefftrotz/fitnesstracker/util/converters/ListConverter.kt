package com.jefftrotz.fitnesstracker.util.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jefftrotz.fitnesstracker.model.Exercise

// TODO: Update Gson to Moshi or other JSON converter
class ListConverter {

    private val gson = Gson()

    @TypeConverter
    fun listToString(exerciseList: List<Exercise>): String {
        return gson.toJson(exerciseList)
    }

    @TypeConverter
    fun stringToList(jsonString: String): List<Exercise> {
        return gson.fromJson(jsonString, object : TypeToken<List<Exercise>>(){}.type)
    }
}