package org.jash.common.utils

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val client by lazy {
    OkHttpClient.Builder()
        .build()
}

private val gson by lazy {
    GsonBuilder()
        .create()
}

val retrofit:Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("http://43.143.157.87:8888/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
}