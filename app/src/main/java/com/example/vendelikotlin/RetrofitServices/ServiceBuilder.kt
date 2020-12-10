package com.example.vendelikotlin.RetrofitServices

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    val baseUrl:String="http://BaseUrl"
    open val client= OkHttpClient.Builder().build()
     val retrofit= Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}