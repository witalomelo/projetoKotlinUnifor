package br.com.client.extensions

import android.widget.ImageView
import br.com.client.R
import coil.load

fun ImageView.tentaCarregarImagem(
    url: String? = null,
    fallback: Int = R.drawable.ic_launcher_foreground
){
    load(url){
        fallback(fallback)
    }
}