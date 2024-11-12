package com.example.firstkotlin.Activity.RetrofitInfo

import com.example.firstkotlin.Activity.Interface.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {

    companion object{
        val base_uri = "https://jsonplaceholder.typicode.com/"
        fun getData() : Retrofit{
            return Retrofit.Builder().baseUrl(base_uri).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}