package com.example.trainingschedule.ui.training

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingschedule.R
import com.example.trainingschedule.ui.data.TrainingRepository
import kotlinx.coroutines.launch


class TrainingViewModel(
    private val source: TrainingRepository
) : ViewModel(){

    val items = MutableLiveData<List<Training>>(emptyList())

    init {
        viewModelScope.launch {
            val training = source.getTraining()
            items.value = training
        }
    }

}