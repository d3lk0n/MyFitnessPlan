package com.example.trainingschedule.ui.calendar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingschedule.ui.exercises.Exercise
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.trainingschedule.ui.data.ExerciseRepository
import kotlinx.coroutines.*
import org.koin.core.KoinApplication.Companion.init

class AddViewModel(
    val source: ExerciseRepository,
    var list: Array<Exercise>
) : ViewModel() {

    val items = MutableLiveData<List<Exercise>>(emptyList())

    init {
        viewModelScope.launch {
            val exercises = source.getExercises()
            items.value = exercises
        }
    }
}