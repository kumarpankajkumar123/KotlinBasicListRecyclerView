package com.example.firstkotlin.Activity.Modals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val countValue = MutableLiveData<Int>(0)

    val liveData : LiveData<Int>
        get() = countValue

    fun getValue(){
        countValue
    }
    fun increment(){
        countValue.value = countValue.value?.plus(1);
    }
    fun decrement(){
        countValue.value = countValue.value?.minus(1)
    }

}