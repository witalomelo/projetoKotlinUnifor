package br.com.client.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
class Obra(
    val nome: String = "",
    val autor: String = "",
    val descricao: String = "",
    val imagem: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int = 0

    companion object : Parceler<Obra> {

        override fun Obra.write(parcel: Parcel, flags: Int) {
            parcel.writeString(nome)
            parcel.writeString(autor)
            parcel.writeString(descricao)
            parcel.writeString(imagem)
        }

        override fun create(parcel: Parcel): Obra {
            return Obra(parcel)
        }
    }
}

