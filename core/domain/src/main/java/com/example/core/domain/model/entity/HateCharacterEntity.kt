package com.example.core.domain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hate_character")
data class HateCharacterEntity(
    @ColumnInfo(name = "character_id") val characterId: Int,
    @ColumnInfo(name = "character_name") val characterName: String?,
    @ColumnInfo(name = "character_image") val characterImage: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}