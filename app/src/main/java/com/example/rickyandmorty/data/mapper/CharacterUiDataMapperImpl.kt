package com.example.rickyandmorty.data.mapper

import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.data.dto.Character
import javax.inject.Inject

class CharacterUiDataMapperImpl @Inject constructor() : Mapper<Character, CharacterUiData> {
    override fun map(input: Character?): CharacterUiData {
        return CharacterUiData(
            id = input?.id,
            image = input?.image.orEmpty(),
            name = input?.name.orEmpty(),
            status = input?.status.orEmpty(),
            species = input?.species.orEmpty(),
            gender = input?.gender.orEmpty(),
            origin = input?.origin,
            location = input?.location,
            episode = input?.episode.orEmpty()
        )
    }
}