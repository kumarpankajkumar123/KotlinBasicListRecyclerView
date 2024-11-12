package com.example.firstkotlin.Activity

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivitySplashScreenBinding

class ListviewKnow : AppCompatActivity() {

    lateinit var binding : ActivitySplashScreenBinding;

    lateinit var list : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.light_login)
        }

        list = arrayListOf("pankaj kumar","abhishek kumar","raja rathi","ashish","pankaj kumar","abhishek kumar",
            "raja rathi","ashish","pankaj kumar","abhishek kumar","raja rathi","ashish"
        ,"pankaj kumar","abhishek kumar","raja rathi","ashish","pankaj kumar","abhishek kumar",
            "raja rathi","ashish","pankaj kumar","abhishek kumar","raja rathi","ashish");

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        binding.listview.adapter = adapter

        binding.listview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position)
            Toast.makeText(this, "$item", Toast.LENGTH_SHORT).show()
        }

    }
}


