package com.example.firstkotlin.Activity.RetrofitInfo

import com.example.firstkotlin.Activity.Interface.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {

    companion object{
        var retrofit: Retrofit? = null
        val base_uri = "https://jsonplaceholder.typicode.com/"
        val base_url = "https://opentdb.com/"
        fun getData() : Retrofit{
            return Retrofit.Builder().baseUrl(base_uri).addConverterFactory(GsonConverterFactory.create()).build()
        }

        fun getQuestion() : Retrofit{
           retrofit =  Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit as Retrofit;
        }
    }
}