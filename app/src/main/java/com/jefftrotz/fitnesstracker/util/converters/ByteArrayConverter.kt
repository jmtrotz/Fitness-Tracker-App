package com.jefftrotz.fitnesstracker.util.converters

import androidx.room.TypeConverter

class ByteArrayConverter {
    @TypeConverter
    fun stringFromByteArray(byteArray: ByteArray): String {
        return byteArray.toString()
    }

    @TypeConverter
    fun byteArrayFromString(string: String): ByteArray {
        return string.toByteArray()
    }
}