package org.jash.common.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TypeConvert {
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    @TypeConverter
    fun fromCreateTime(date: Date):String = sdf.format(date)
    @TypeConverter
    fun toCreateTime(src:String):Date = sdf.parse(src)
}