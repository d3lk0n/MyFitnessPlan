package com.example.trainingschedule.ui.data

import android.util.Log
import com.example.trainingschedule.ui.exercises.Exercise
import com.example.trainingschedule.ui.training.Training
import com.google.gson.Gson
import okhttp3.MediaType
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.RequestBody

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.create
import retrofit2.http.*
import java.io.*
import java.lang.Exception


interface RemoteDataSource {
    suspend fun getExercises(): List<Exercise>
    suspend fun getExercisesAmount(): Int
    suspend fun editExercise(exercise: Exercise)
    suspend fun deleteExercise(id: Long)
    suspend fun updateExercises(exercises: List<Exercise>)

    suspend fun getTraining(): List<Training>
    suspend fun getTrainingAmount(): Int
    suspend fun getTrainingExercises(id: Long): List<Exercise>
    fun addTraining(training: Training)
}

class RetrofitDataSource : RemoteDataSource, KoinComponent {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/d3lk0n/trainingsplan/main/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api = retrofit.create(JsonPlaceholderApi::class.java)

    private val retrofitUp = Retrofit.Builder()
        .baseUrl("https://github.com/d3lk0n/trainingsplan/blob/main/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val client = retrofitUp.create(JsonPlaceholderApi::class.java)

    override suspend fun getExercises(): List<Exercise> {
        Log.d("Get", "Finding all Exercises")
        val allExercises = api.getExercises()
        val subList = mutableListOf<Exercise>()
        //search for exercises with deleted==false and only show those
        for (i in allExercises.indices) {
            if (!allExercises[i].deleted)
                subList.add(allExercises[i])
        }
        return subList
    }

    override suspend fun getExercisesAmount(): Int {
        val amount = api.getExercises().size
        Log.d("Get", "Amount of total Exercises: $amount")
        return amount
    }

    override suspend fun editExercise(exercise: Exercise) {

        Log.d(
            "Edit",
            "Adding Element at index ${exercise.id}, with name==${exercise.name}" +
                    ", difficulty==${exercise.difficulty}, description==${exercise.description}"
        )
        val subExercises = api.getExercises().toMutableList()
        Log.d("Test", "Size of current List: ${subExercises.size}")
        subExercises[exercise.id.toInt()-1] = exercise
        updateExercises(subExercises)
    }

    override suspend fun deleteExercise(id: Long) {
        Log.d("Delete", "Deleting Element at index $id")
        val subExercises = api.getExercises()
        subExercises[id.toInt()-1].deleted = true
        updateExercises(subExercises)
    }

    override suspend fun updateExercises(exercises: List<Exercise>) {
        Log.d("Update", "Updating Remote Source")
        val gson = Gson()
        val json = gson.toJson(exercises)
        Log.d("Update", "Created Json with $json")

        try {
            client.updateExercises(exercises)
        } catch (e:Exception) {
            e.printStackTrace()
        }

        //Upload Files
        /*val file = File("test.json")
        file.writeText(json)
        val mediaPart = MediaType.parse("application/json; charset=utf-8")
        val filePart: RequestBody = RequestBody.create(mediaPart, file)

        val fileM: MultipartBody.Part = MultipartBody.Part.createFormData("data", "upload.json", filePart)

        val call: Call<ResponseBody> = client.uploadFile(fileM)*/

    }


    override suspend fun getTraining(): List<Training> {
        Log.d("Get", "Finding all Training")
        return api.getTraining()
    }

    override suspend fun getTrainingAmount(): Int {
        val amount = api.getTraining().size
        Log.d("Get", "Amount of total Training: $amount")
        return amount
    }

    override suspend fun getTrainingExercises(id: Long): List<Exercise> {
        return api.getTraining()[id.toInt() - 1].exercises
    }

    override fun addTraining(training: Training) {
        try {
            api.addTraining(training)
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }

    /*
    interface UploadService {
        @Multipart
        @POST("test.json")
        fun uploadFile (
            @Part file: MultipartBody.Part
            // additional info, cookies...
        ): Call<ResponseBody>
    }*/

    interface JsonPlaceholderApi {
        @GET("exercises.json")
        suspend fun getExercises(): List<Exercise>

        @POST("test.json")
        fun updateExercises(exercises:List<Exercise>)

        @PUT("test.json")
        fun addTraining(training: Training)

        @GET("training.json")
        suspend fun getTraining(): List<Training>

    }
}