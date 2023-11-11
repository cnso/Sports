package org.jash.sports.net

import org.jash.sports.entry.Category
import org.jash.sports.entry.Res
import retrofit2.http.GET

interface Service {
    @GET("/api/nt/all")
    suspend fun getAllCategory(): Res<List<Category>>
}