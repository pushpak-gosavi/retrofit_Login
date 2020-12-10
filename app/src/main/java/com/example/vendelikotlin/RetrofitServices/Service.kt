package com.example.vendelikotlin.RetrofitServices

import com.example.vendelikotlin.Models.Login
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Service {
    @FormUrlEncoded
    @POST("oauth/token")
    fun getToken(@Field ("grant_type")grant_type:String,
    @Field("client_id") client_id:Int,
    @Field("client_secret") client_secret:String,
    @Field("username") username:String,
    @Field("password") password:String) : Call<Login>
}