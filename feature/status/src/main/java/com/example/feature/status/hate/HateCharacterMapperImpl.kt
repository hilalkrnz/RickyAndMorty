package com.example.feature.status.hate

import com.example.core.common.mapper.ListMapper
import com.example.core.domain.model.entity.HateCharacterEntity
import com.example.core.ui.model.HateCharacter
import javax.inject.Inject

class HateCharacterMapperImpl @Inject constructor() :
    ListMapper<HateCharacterEntity, HateCharacter> {
    override fun map(input: List<HateCharacterEntity>?): List<HateCharacter> {
        return input?.map { hateCharacterEntity ->
            HateCharacter(
                characterId = hateCharacterEntity.characterId,
                characterName = hateCharacterEntity.characterName,
                characterImage = hateCharacterEntity.characterImage
            )

        } ?: emptyList()
    }
}