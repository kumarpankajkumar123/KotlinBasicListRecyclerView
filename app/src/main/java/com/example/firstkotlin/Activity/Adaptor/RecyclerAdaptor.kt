package com.example.firstkotlin.Activity.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.Activity.Modals.RecyclerData
import com.example.firstkotlin.R

class RecyclerAdaptor(val listdata : List<RecyclerData>) : RecyclerView.Adapter<RecyclerAdaptor.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_design,parent,false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
      return listdata.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentposition = listdata.get(position)
        holder.image.setImageResource(currentposition.image)
        holder.text.text = currentposition.tittle
    }

    class MyViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
          val image : ImageView = itemview.findViewById(R.id.image)
          val text : TextView = itemview.findViewById(R.id.text)
    }
}