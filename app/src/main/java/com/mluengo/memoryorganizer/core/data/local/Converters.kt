package com.mluengo.memoryorganizer.core.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class Converters(val moshi: Moshi) {
    @TypeConverter
    fun fromListToJson(value: List<String>): String? {
        val type = Types.newParameterizedType(List::class.java, String::class.javaObjectType)
        val adapter = moshi.adapter<List<String>>(type)

        return adapter.toJson(value)
    }

    @TypeConverter
    fun fromJsonToList(json: String): List<String>? {
        val type = Types.newParameterizedType(List::class.java, String::class.javaObjectType)
        val adapter = moshi.adapter<List<String>>(type)

        return adapter.fromJson(json)
    }
}