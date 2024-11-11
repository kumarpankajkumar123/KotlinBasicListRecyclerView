package com.example.firstkotlin.Activity.Interface

import okhttp3.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    suspend fun getData():Response



}