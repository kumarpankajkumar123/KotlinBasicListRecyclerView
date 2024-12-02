package com.example.firstkotlin.Activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlin.Activity.Modals.MyViewModel
import com.example.firstkotlin.databinding.ActivityMyViewModelDemoBinding

class MyViewModelDemo : AppCompatActivity() {
    lateinit var binding : ActivityMyViewModelDemoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMyViewModelDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}