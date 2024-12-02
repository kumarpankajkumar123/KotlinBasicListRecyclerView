package com.example.firstkotlin.Activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstkotlin.Activity.Adaptor.RecyclerAdaptor
import com.example.firstkotlin.Activity.Modals.RecyclerData
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.ActivityTamangSplashScreenBinding

class RecyclerKnow : AppCompatActivity() {

    lateinit var listview : ArrayList<RecyclerData>
    lateinit var imagelist : Array<Int>
    lateinit var textlist : Array<String>

    lateinit var binding : ActivityTamangSplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTamangSplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imagelist = arrayOf(
            R.drawable.cock,
            R.drawable.kingfisher,
            R.drawable.owl,
            R.drawable.crow,
            R.drawable.eagle,
            R.drawable.parrot,
            R.drawable.peacock,
            R.drawable.pigeon,
            R.drawable.crow,
            R.drawable.eagle,
            R.drawable.parrot,
            R.drawable.peacock,
            R.drawable.pigeon
        )

        textlist = arrayOf("cock","kingfisher","owl","crow","eagle","parrot","peacock","pigeon","crow","eagle","parrot","peacock","pigeon")

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.hasFixedSize()

        listview = arrayListOf<RecyclerData>()
        getData()
    }

    fun getData(){

        for(i in imagelist.indices){
            val dataclass = RecyclerData(imagelist[i],textlist[i])
            listview.add(dataclass)
        }
        binding.recycler.adapter = RecyclerAdaptor(listview)

    }
}