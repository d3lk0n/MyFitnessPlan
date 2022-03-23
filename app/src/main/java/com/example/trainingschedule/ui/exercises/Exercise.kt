package com.example.trainingschedule.ui.exercises

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LifecycleOwner
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Exercise(
    val id: Long,
    var difficulty: Long,
    var name: String?,
    var description: String?,
    var deleted: Boolean
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(difficulty)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeByte(if (deleted) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Exercise> {
        override fun createFromParcel(parcel: Parcel): Exercise {
            return Exercise(parcel)
        }

        override fun newArray(size: Int): Array<Exercise?> {
            return arrayOfNulls(size)
        }
    }
}
