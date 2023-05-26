package com.example.core.ui


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUiData(
    val id: Int?,
    val name: String?,
    val image: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val location: String?
) : Parcelable
