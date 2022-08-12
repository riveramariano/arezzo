package com.arezzo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sucursal(
    var id: String,
    val ubicacion: String,
    val gerente: String,
    val telefono: String,
    val rutaImagen: String?,
) : Parcelable {
    constructor(): this("", "", "", "", "")
}