package com.example.vendelikotlin.SharedPreManager

import android.content.Context
import android.content.SharedPreferences
import com.example.vendelikotlin.Models.Login

class SharedPrefManager(context:Context) {
    val modePrivate:Int= 0
    val sharedPrefName="VedeliKotlin"
    val username:String= "UserName"
    val accessToken:String= "AccessToken"
    val sharedPreferences=context.getSharedPreferences(sharedPrefName,modePrivate)

    fun setUser(login: Login) {
        var editor= sharedPreferences.edit()
        editor.putString(username,login.username)

        editor.putString(accessToken,login.access_token)
        editor.commit()
    }
    fun isLogin():Boolean{
        return sharedPreferences.getString(username,null)!=null
    }
    // getUser
    fun getUser():Login{
        return Login(sharedPreferences.getString(accessToken,null),
        sharedPreferences.getString(username,null))
    }
}