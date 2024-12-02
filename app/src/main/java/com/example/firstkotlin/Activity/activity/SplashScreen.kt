package com.example.firstkotlin.Activity.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.CalendarContract.Colors
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstkotlin.R

class SplashScreen : AppCompatActivity() {
    lateinit var sharedpreference : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen2)

        sharedpreference = getSharedPreferences("myProfile", MODE_PRIVATE)

        val window = window;
        window.statusBarColor = ContextCompat.getColor(this, R.color.light_login)

       val log =  sharedpreference.getBoolean("isLogged",false)
        Log.e("isloogin",":="+log);
        Handler(Looper.getMainLooper()).postDelayed({
            // Switch to AnotherActivity
            if(log){
                val intent = Intent(this, GetResponseQuizeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, QuizApp::class.java)
                startActivity(intent)
                finish()
            }

             // Optional: Close the current activity
        }, 4000)


    }
}