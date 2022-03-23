package com.example.trainingschedule.ui.exercises

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingschedule.R
import com.example.trainingschedule.ui.data.ExerciseRepository
import com.example.trainingschedule.ui.data.RemoteDataSource
import kotlinx.coroutines.launch

class ExercisesViewModel(
    val source : ExerciseRepository
) : ViewModel()
{
    val items = MutableLiveData<List<Exercise>>(emptyList())
    var listLength: Int = 0

    init {
        viewModelScope.launch {
           val exercises = source.getExercises()
            listLength = source.getAmount()
            //All Exercises
            items.value = exercises
        }
    }
}