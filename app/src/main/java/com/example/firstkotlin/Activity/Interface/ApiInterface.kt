package com.example.firstkotlin.Activity.Interface


import com.example.firstkotlin.Activity.Modals.ResponseData
import retrofit2.Call

import retrofit2.http.GET

interface ApiInterface {

    @GET("/posts")
    fun getData(): Call<ArrayList<ResponseData>>
}