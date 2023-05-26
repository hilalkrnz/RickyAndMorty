package com.example.core.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoveCharacter(
    val characterId: Int?,
    val characterName: String?,
    val characterImage: String?
) : Parcelable {
    var id: Int = 0
}
