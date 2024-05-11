package org.d3if3073.asesmen2.model

import androidx.annotation.DrawableRes

data class Catatan(
    @DrawableRes val gambarResId: Int? = null,
    val judul: String,
    val harga: String,
    val catatan: String
)