package com.example.trainingschedule.ui.di


import com.example.trainingschedule.ui.calendar.AddViewModel
import com.example.trainingschedule.ui.calendar.CalendarViewModel
import com.example.trainingschedule.ui.data.ExerciseRepository
import com.example.trainingschedule.ui.data.ExerciseRepositoryImpl
import com.example.trainingschedule.ui.data.TrainingRepository
import com.example.trainingschedule.ui.data.TrainingRepositoryImpl
import com.example.trainingschedule.ui.data.RemoteDataSource
import com.example.trainingschedule.ui.data.RetrofitDataSource
import com.example.trainingschedule.ui.exercises.ExercisesViewModel
import com.example.trainingschedule.ui.exercises.buttons.EditViewModel
import com.example.trainingschedule.ui.exercises.buttons.DeleteViewModel
import com.example.trainingschedule.ui.home.HomeViewModel
import com.example.trainingschedule.ui.training.TrainingViewModel
import com.example.trainingschedule.ui.training.shortexercises.ShortExercisesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<RemoteDataSource> { RetrofitDataSource() }
    single<ExerciseRepository> { ExerciseRepositoryImpl(get()) }
    single<TrainingRepository> { TrainingRepositoryImpl(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { ExercisesViewModel(get()) }
    viewModel { TrainingViewModel(get()) }
    viewModel { params ->
        AddViewModel(
            source = get(),
            list = params.get()
        )
    }
    viewModel { params ->
        CalendarViewModel(
            sourceTraining = get(),
            sourceExercises = get(),
            list = params.get()
        )
    }
    viewModel { params ->
        EditViewModel(
            source = get(),
            exercise = params.get()
        )
    }
    viewModel { params ->
        DeleteViewModel(
            source = get(),
            id = params.get()
        )
    }
    viewModel { params ->
        ShortExercisesViewModel(
            source = get(),
            id = params.get()
        )
    }
}