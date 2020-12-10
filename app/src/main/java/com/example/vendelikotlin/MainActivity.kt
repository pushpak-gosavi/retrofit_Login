package com.example.vendelikotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vendelikotlin.Models.Login
import com.example.vendelikotlin.RetrofitServices.Constants
import com.example.vendelikotlin.RetrofitServices.Service
import com.example.vendelikotlin.RetrofitServices.ServiceBuilder
import com.example.vendelikotlin.SharedPreManager.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPrefManager:SharedPrefManager=SharedPrefManager(applicationContext)
        btnLogin.setOnClickListener {
            if(sharedPrefManager.isLogin()){
                val userName:String= sharedPrefManager.getUser().username.toString().trim()
                Toast.makeText(this@MainActivity,userName,Toast.LENGTH_LONG).show()
        }else{
                val userName:String= edtUsername.text.toString().trim()
                val password:String= edtPassword.text.toString().trim()
                val serviceBuilder= ServiceBuilder.retrofit.create(Service::class.java)
                serviceBuilder.getToken(Constants.grant_type,Constants.client_id,Constants.client_secret,userName,password)
                    .enqueue(object:Callback<Login>{
                        override fun onResponse(call: Call<Login>, response: Response<Login>) {
                            Toast.makeText(this@MainActivity,"successfully",Toast.LENGTH_LONG).show()
                            try{
                                val login= response.body()
                                var userName=edtUsername.text.toString().trim()
                                login.username=userName
                                sharedPrefManager.setUser(login)
                                // sharedPrefManager.setPref(userName,login.access_token)
                            }catch (ex:Exception){
                                ex.message
                            }
                        }

                        override fun onFailure(call: Call<Login>, t: Throwable) {
                            Toast.makeText(this@MainActivity,"Failed",Toast.LENGTH_LONG).show()
                        }
                    })

            }
        }
    }
}