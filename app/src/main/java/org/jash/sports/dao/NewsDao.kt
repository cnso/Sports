package org.jash.sports.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import org.jash.sports.entry.Category
import org.jash.sports.entry.News

@Dao
interface  NewsDao {
    @Upsert
    suspend fun insert(vararg news: News)
    @Query("select * from news where id=:id")
    suspend fun findById(id:Int):News
}