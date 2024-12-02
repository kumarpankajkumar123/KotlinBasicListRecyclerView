package com.example.firstkotlin.Activity.Interface


import com.example.firstkotlin.Activity.Modals.QuestionModal
import com.example.firstkotlin.Activity.Modals.ResponseData
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("posts")
    fun getData(): Call<ResponseData>

    @GET("api.php")
    fun getQuestions(@Query("amount") questionQuantity : Int,@Query("category") c_id :Int,@Query("difficulty") difficulty : String, @Query("type") type : String) : Call<QuestionModal>
}