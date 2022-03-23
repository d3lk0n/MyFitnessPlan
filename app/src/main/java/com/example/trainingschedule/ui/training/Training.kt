package com.example.trainingschedule.ui.training

import android.os.Parcel
import android.os.Parcelable
import com.example.trainingschedule.ui.exercises.Exercise
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Training(
    val trainingId: Long,
    val dateAdded: String?,
    val exercises: List<Exercise>
)