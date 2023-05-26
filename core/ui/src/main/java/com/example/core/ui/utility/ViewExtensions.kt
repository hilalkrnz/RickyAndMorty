package com.example.core.ui.utility

import androidx.appcompat.widget.AppCompatImageView
import coil.load
import coil.size.Scale
import com.example.core.ui.R

fun AppCompatImageView.loadImage(imageUrl: String?) {
    val placeholders: Int = R.drawable.placeholder
    load(imageUrl) {
        scale(Scale.FILL)
        placeholder(placeholders)
    }
}
