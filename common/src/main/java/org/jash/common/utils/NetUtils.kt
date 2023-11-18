package org.jash.common.utils

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
var token:String? = null
private val client by lazy {
    OkHttpClient.Builder()
        .addInterceptor {
            if (token != null) {
                it.proceed(it.request().newBuilder().addHeader("sn_token", token).build())
            } else {
                it.proceed(it.request())
            }
        }
        .build()
}

private val gson by lazy {
    GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
}

val retrofit:Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("http://10.161.9.80:7014/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}