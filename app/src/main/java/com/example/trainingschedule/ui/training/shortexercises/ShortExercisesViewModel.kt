package com.example.trainingschedule.ui.training.shortexercises

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingschedule.R
import com.example.trainingschedule.ui.data.ExerciseRepository
import com.example.trainingschedule.ui.data.RemoteDataSource
import com.example.trainingschedule.ui.data.TrainingRepository
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.training.Training
import kotlinx.coroutines.launch

class ShortExercisesViewModel(
    val id: Long,
    private val source : TrainingRepository
) : ViewModel()
{
    val items = MutableLiveData<List<Exercise>>(emptyList())

    init {
        viewModelScope.launch {
           val exercises = source.getTrainingExercises(id)
            items.value = exercises
        }
    }
}