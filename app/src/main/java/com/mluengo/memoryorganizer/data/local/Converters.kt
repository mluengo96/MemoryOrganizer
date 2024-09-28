package com.mluengo.memoryorganizer.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class Converters(val moshi: Moshi) {
    @TypeConverter
    fun fromListToJson(value: List<Int>): String? {
        val type = Types.newParameterizedType(List::class.java, Int::class.javaObjectType)
        val adapter = moshi.adapter<List<Int>>(type)

        return adapter.toJson(value)
    }

    @TypeConverter
    fun fromJsonToList(json: String): List<Int>? {
        val type = Types.newParameterizedType(List::class.java, Int::class.javaObjectType)
        val adapter = moshi.adapter<List<Int>>(type)

        return adapter.fromJson(json)
    }
}