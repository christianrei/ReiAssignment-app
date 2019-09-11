package com.example.reiassignment.models

import android.os.Parcel
import android.os.Parcelable

data class PostData(
    val id: String,
    val author: String,
    val score: Int,
    val url: String,
    val selfText: String,
    val numComments: Int,
    val thumbnail: String,
    val title: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(author)
        parcel.writeInt(score)
        parcel.writeString(url)
        parcel.writeString(selfText)
        parcel.writeInt(numComments)
        parcel.writeString(thumbnail)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostData> {
        override fun createFromParcel(parcel: Parcel): PostData {
            return PostData(parcel)
        }

        override fun newArray(size: Int): Array<PostData?> {
            return arrayOfNulls(size)
        }
    }
}