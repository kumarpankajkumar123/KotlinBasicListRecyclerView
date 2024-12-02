package com.example.firstkotlin.Activity.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.Activity.Interface.ApiInterface
import com.example.firstkotlin.Activity.Modals.ResponseData
import com.example.firstkotlin.Activity.RetrofitInfo.RetrofitConnection
import com.example.firstkotlin.R

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LearRetrofit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lear_retrofit)

       val result = RetrofitConnection.getData().create(ApiInterface::class.java).getData()

      Log.e("the request code",":="+  result.request())

        result.enqueue(object : Callback<ResponseData?> {
            override fun onResponse(p0: Call<ResponseData?>, response: Response<ResponseData?>) {
               if(response.isSuccessful){
                   Log.e("the body","${response.body()?.get(0)!!.body}")
               }
                else{
                   Log.e("the error","errorr aa gyi")
               }
            }

            override fun onFailure(p0: Call<ResponseData?>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })


}}