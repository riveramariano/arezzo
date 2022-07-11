package com.arezzo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Libro(
    var id: String,
    val nombre: String,
    val unidades: Int,
    val autor: String,
    val rutaAudio: String?,
    val rutaImagen: String?,
) : Parcelable {
    constructor(): this("", "", 0, "", "", "")
}