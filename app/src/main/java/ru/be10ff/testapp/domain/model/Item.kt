package ru.be10ff.testapp.domain.model

import android.graphics.Bitmap

data class Item(
    val roomId: Int = 0,
    val source: String,
    val img: Bitmap? = null
)
