package com.example.trainingschedule.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingschedule.ui.data.ExerciseRepository

class HomeViewModel(
    val source : ExerciseRepository
) : ViewModel() {

    /*private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text*/
}