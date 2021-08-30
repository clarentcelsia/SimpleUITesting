package com.example.testingexample.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val id: Int,
    val title: String,
    @DrawableRes val image: Int,
    val author: ArrayList<String>,
    val desc: String
):Parcelable
{
    private companion object: Parceler<Data>{
        override fun create(parcel: Parcel): Data {
            return Data(
                parcel.readInt(),
                parcel.readString()!!,
                parcel.readInt(),
                parcel.createStringArrayList()!!,
                parcel.readString()!!
            )
        }

        override fun Data.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(title)
            parcel.writeInt(image)
            parcel.writeStringList(author)
            parcel.writeString(desc)
        }

    }
}
