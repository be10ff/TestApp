package ru.be10ff.testapp.data.remote.data

import android.graphics.Bitmap

data class Picture (
    val id: Int,
    val source: String,
    val bitmap : Bitmap?
)