package com.example.firstkotlin.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstkotlin.databinding.ActivityTamangSplashScreenBinding

class RecyclerKnow : AppCompatActivity() {
    lateinit var binding : ActivityTamangSplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTamangSplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}