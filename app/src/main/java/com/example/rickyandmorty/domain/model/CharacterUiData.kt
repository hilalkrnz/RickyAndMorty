package com.example.rickyandmorty.domain.model

import android.os.Parcelable
import com.example.rickyandmorty.data.dto.Location
import com.example.rickyandmorty.data.dto.Origin
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUiData(
    val id: Int?,
    val name: String?,
    val image: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val origin: Origin?,
    val location: Location?,
    val episode: List<String>?
) : Parcelable
