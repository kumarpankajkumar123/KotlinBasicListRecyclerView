package com.example.firstkotlin.Activity.activity

import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivityResultsBinding

class Results : AppCompatActivity() {
    lateinit var binding : ActivityResultsBinding
    lateinit var sharedpreference : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpreference = getSharedPreferences("myProfile", MODE_PRIVATE)

        val score = intent.getIntExtra("totalScore", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)

        binding.score.text = score.toString()+"/"+totalQuestions.toString();
        val name = sharedpreference.getString("name","pankaj")
        binding.name.text = name

        val url = sharedpreference.getString("profile_image_uri",null)

        if(url != null){
            Glide.with(this).load(Uri.parse(url)).into(binding.profileImage)
        }
        val percentage = (score * 100)/totalQuestions
       val ani =  getAnimationForPercentage(percentage)

       binding.animationView.setAnimation(ani)
        binding.animationView.playAnimation()

    }

    fun getAnimationForPercentage(percentage: Int): Int {
        return when (percentage) {
            in 0..30 -> R.raw.nice // Low percentage animation
            in 31..60 -> R.raw.nice // Medium percentage animation
            in 61..100 -> R.raw.happy // High percentage animation
            else -> R.raw.response
        }
    }

}