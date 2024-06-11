package com.jefftrotz.fitnesstracker.util.converters

import androidx.room.TypeConverter
import com.jefftrotz.fitnesstracker.model.Exercise
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ListConverter {

    private val type = Types.newParameterizedType(List::class.java, Exercise::class.java)
    private val moshi = Moshi.Builder().build()
    private val adapter = moshi.adapter<List<Exercise>>(type)

    @TypeConverter
    fun listToString(exerciseList: List<Exercise>): String {
        return adapter.toJson(exerciseList)
    }

    @TypeConverter
    fun stringToList(jsonString: String): List<Exercise> {
        return adapter.fromJson(jsonString) ?: emptyList()
    }
}