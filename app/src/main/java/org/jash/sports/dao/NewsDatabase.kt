package org.jash.sports.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import org.jash.common.utils.TypeConvert
import org.jash.sports.entry.Category

@Database(entities = [Category::class], version = 1)
@TypeConverters(TypeConvert::class)
abstract class NewsDatabase:RoomDatabase() {
    abstract fun getCategoryDao():CategoryDao
}