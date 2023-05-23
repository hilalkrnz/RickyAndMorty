package com.example.rickyandmorty.utility

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.widget.AppCompatImageView
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import com.example.rickyandmorty.R

fun AppCompatImageView.loadImage(imageUrl: String?) {
    val placeholders: Int = R.drawable.placeholder
    load(imageUrl) {
        scale(Scale.FILL)
        placeholder(placeholders)
    }
}

suspend fun AppCompatImageView.loadImageFromUrl(imageUrl: String?) {
    val loading = ImageLoader(this.context)
    val request = ImageRequest.Builder(this.context)
        .data(imageUrl)
        .build()
    val result = (loading.execute(request) as SuccessResult).drawable
    val bitmap = (result as BitmapDrawable).bitmap
    this.setImageBitmap(bitmap)
}