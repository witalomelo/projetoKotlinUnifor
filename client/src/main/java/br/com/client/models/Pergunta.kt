package br.com.client.models

import android.os.Parcel
import android.os.Parcelable

data class Pergunta(
    val id: String = "",
    val pergunta: String = "",
    val resposta: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(pergunta)
        parcel.writeString(resposta)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Pergunta> {
        override fun createFromParcel(parcel: Parcel): Pergunta {
            return Pergunta(parcel)
        }

        override fun newArray(size: Int): Array<Pergunta?> {
            return arrayOfNulls(size)
        }
    }
}
