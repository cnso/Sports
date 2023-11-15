package org.jash.sports.net

import org.jash.sports.entry.Category
import org.jash.sports.entry.News
import org.jash.sports.entry.Page
import org.jash.sports.entry.Res
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("/api/nt/all")
    suspend fun getAllCategory(): Res<List<Category>>
    @GET("/api/news/page")
    suspend fun getNewsByPage(@Query("type") type:Int,@Query("page") page:Int,@Query("size") size:Int): Res<Page<News>>
}