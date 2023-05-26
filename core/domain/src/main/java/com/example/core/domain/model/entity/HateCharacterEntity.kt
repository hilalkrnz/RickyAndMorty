package com.example.core.domain.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "hate_character")
@Parcelize
data class HateCharacterEntity(
    @ColumnInfo(name = "character_id") val characterId: Int,
    @ColumnInfo(name = "character_name") val characterName: String?,
    @ColumnInfo(name = "character_image") val characterImage: String?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}