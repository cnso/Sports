package org.jash.sports.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import org.jash.sports.entry.Category

@Dao
interface  CategoryDao {
    @Upsert
    suspend fun insert(vararg category: Category)
    @Query("select * from category where id=:id")
    suspend fun findById(id:Int):Category
}