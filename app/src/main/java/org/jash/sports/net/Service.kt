package org.jash.sports.net

import okhttp3.RequestBody
import org.jash.sports.entry.Category
import org.jash.sports.entry.News
import org.jash.sports.entry.Page
import org.jash.sports.entry.Res
import org.jash.sports.entry.Sign
import org.jash.sports.entry.User
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Service {
    @GET("/api/nt/all")
    suspend fun getAllCategory(): Res<List<Category>>
    @GET("/api/news/page")
    suspend fun getNewsByPage(@Query("type") type:Int,@Query("page") page:Int,@Query("size") size:Int): Res<Page<News>>
    @GET("/api/sms/sendrcode")
    suspend fun getCode(@Query("phone") phone:String):Res<Int>
    @POST("/api/sms/checkrcode")
    suspend fun signPhone(@Body sign:Sign):Res<Any?>
    @POST("/api/user/register")
    suspend fun registry(@Body user:User):Res<Any?>
}