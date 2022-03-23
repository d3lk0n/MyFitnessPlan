package com.example.trainingschedule.ui.exercises.buttons

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingschedule.ui.exercises.Exercise
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.trainingschedule.ui.data.ExerciseRepository
import org.koin.core.KoinApplication.Companion.init

class EditViewModel(
    val source: ExerciseRepository,
    val exercise: Exercise
) : ViewModel() {

    fun editInRepo (exercise:Exercise) {
        viewModelScope.launch {
            source.editExercise(exercise)
        }
    }

}