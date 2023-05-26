package com.example.feature.status.love

import com.example.core.common.mapper.ListMapper
import com.example.core.domain.model.entity.LoveCharacterEntity
import com.example.core.ui.model.LoveCharacter
import javax.inject.Inject

class LoveCharacterMapperImpl @Inject constructor() :
ListMapper<LoveCharacterEntity, LoveCharacter>{
    override fun map(input: List<LoveCharacterEntity>?): List<LoveCharacter> {
        return input?.map {loveCharacterEntity ->
            LoveCharacter(
                characterId = loveCharacterEntity.characterId,
                characterName = loveCharacterEntity.characterName,
                characterImage = loveCharacterEntity.characterImage
            )
        } ?: emptyList()
    }
}