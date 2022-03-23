package com.example.trainingschedule.ui.data

import android.util.Log
import com.example.trainingschedule.ui.exercises.Exercise


interface ExerciseRepository {

    fun createEmptyExercise(id: Int): Exercise
    suspend fun getExercises(): List<Exercise>
    suspend fun getAmount(): Int
    suspend fun deleteExercise(id:Long)
    suspend fun editExercise(exercise:Exercise)
}

class ExerciseRepositoryImpl(
    private val dataSource: RemoteDataSource
): ExerciseRepository {

    override fun createEmptyExercise(id: Int): Exercise {
        val longId = id.toLong()
        return Exercise(longId, 1, "Exercise Name", "Exercise Description", false)
    }

    override suspend fun getExercises(): List<Exercise> {
        return dataSource.getExercises()
    }

    override suspend fun getAmount():Int {
        return dataSource.getExercisesAmount()
    }

    override suspend fun deleteExercise(id: Long) {
        dataSource.deleteExercise(id)
    }

    override  suspend fun editExercise(exercise:Exercise) {
        dataSource.editExercise(exercise)
    }
}