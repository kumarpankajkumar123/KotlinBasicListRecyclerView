package com.example.firstkotlin.Activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlin.Activity.Modals.MyViewModel
import com.example.firstkotlin.databinding.ActivityDemoConceptBinding

class DemoConcept : AppCompatActivity() {

    lateinit var binding: ActivityDemoConceptBinding;

    lateinit var myviewmodel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoConceptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myviewmodel = ViewModelProvider(this).get(MyViewModel::class.java);


    }
}