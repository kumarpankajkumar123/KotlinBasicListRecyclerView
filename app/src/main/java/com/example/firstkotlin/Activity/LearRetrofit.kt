package com.example.firstkotlin.Activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstkotlin.Activity.Interface.ApiInterface
import com.example.firstkotlin.Activity.Modals.ResponseData
import com.example.firstkotlin.Activity.RetrofitInfo.RetrofitConnection
import com.example.firstkotlin.R

import retrofit2.Call
import retrofit2.Callback

class LearRetrofit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lear_retrofit)

       val result = RetrofitConnection.getData().create(ApiInterface::class.java).getData()

      Log.e("the request code",":="+  result.request())



}}