package com.example.trainingschedule.ui.exercises.buttons


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.trainingschedule.ui.data.ExerciseRepository
import com.example.trainingschedule.ui.exercises.Exercise

class DeleteViewModel(
    val source: ExerciseRepository,
    val id: Long
) : ViewModel() {

    fun deleteInRepo (id:Long) {
        viewModelScope.launch {
            source.deleteExercise(id)
        }
    }

}