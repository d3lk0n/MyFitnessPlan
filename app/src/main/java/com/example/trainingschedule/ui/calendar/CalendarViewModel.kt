package com.example.trainingschedule.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingschedule.ui.data.ExerciseRepository
import com.example.trainingschedule.ui.data.TrainingRepository
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.training.Training
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CalendarViewModel(
    val sourceTraining: TrainingRepository,
    private val sourceExercises: ExerciseRepository,
    var list: Array<Exercise>
) : ViewModel() {

    val items = MutableLiveData<List<Exercise>>(emptyList())

    init {
        if (list.last().id.toInt() != 0) {
            viewModelScope.launch {
                items.value = list.toList()
            }
        }
    }

    fun getNextId(): Int = runBlocking {
        return@runBlocking sourceTraining.getAmount()
    }

}