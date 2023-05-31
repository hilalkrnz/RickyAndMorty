package com.example.feature.detail

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import com.example.core.common.utils.FEMALE
import com.example.core.common.utils.MALE
import com.example.core.common.utils.StatusState

fun TextView.setStatusTextColor(status: String?) {
    val colorRes = when (status) {
        StatusState.ALIVE.statusTitle -> Color.GREEN
        StatusState.DEAD.statusTitle -> Color.RED
        StatusState.UNKNOWN.statusTitle -> Color.BLUE
        else -> Color.BLACK
    }
    setTextColor(colorRes)
}

fun ImageView.setGenderImage(gender: String?) {
    val imageRes = when (gender) {
        String.FEMALE -> R.drawable.gender_female_icon
        String.MALE -> R.drawable.gender_male_icon
        else -> R.drawable.unknown_gender
    }
    setImageResource(imageRes)
}
