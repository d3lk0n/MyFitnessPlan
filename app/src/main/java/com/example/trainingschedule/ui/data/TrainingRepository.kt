package com.example.trainingschedule.ui.data

import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.training.Training


interface TrainingRepository {

    suspend fun getTraining(): List<Training>
    suspend fun getTrainingExercises(id:Long): List<Exercise>
    suspend fun getAmount(): Int
    fun addTraining(training: Training)

}

class TrainingRepositoryImpl(
    private val dataSource: RemoteDataSource
): TrainingRepository {

    override suspend fun getTraining(): List<Training> {
        return dataSource.getTraining()
    }

    override suspend fun getTrainingExercises(id:Long): List<Exercise> {
        return dataSource.getTrainingExercises(id)
    }

    override suspend fun getAmount():Int {
        return dataSource.getTrainingAmount()
    }

    override fun addTraining(training: Training) {
        dataSource.addTraining(training)
    }
}