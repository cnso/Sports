package org.jash.common.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class LoadDB<DB:RoomDatabase>(val clazz:Class<DB>, val name:String):ReadOnlyProperty<Context, DB> {
    @Volatile
    private var value:DB? = null
    override fun getValue(thisRef: Context, property: KProperty<*>): DB {
        if (value != null) {
            return value!!
        }
        return synchronized(this) {
            if(value == null) {
                value = Room.databaseBuilder(thisRef.applicationContext, clazz, name)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            value!!
        }
    }
}