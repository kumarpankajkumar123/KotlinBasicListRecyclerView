package com.example.firstkotlin.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.databinding.ActivitySplashScreenBinding

class ListviewKnow : AppCompatActivity() {

    lateinit var binding : ActivitySplashScreenBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}