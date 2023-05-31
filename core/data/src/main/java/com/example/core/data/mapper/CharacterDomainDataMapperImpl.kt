package com.example.core.data.mapper

import com.example.core.common.mapper.Mapper
import com.example.core.domain.model.CharacterDomainData
import com.example.core.data.dto.Character
import javax.inject.Inject

class CharacterDomainDataMapperImpl @Inject constructor() :
    Mapper<Character, CharacterDomainData> {
    override fun map(input: Character?): CharacterDomainData {
        return CharacterDomainData(
            id = input?.id,
            image = input?.image.orEmpty(),
            name = input?.name.orEmpty(),
            status = input?.status.orEmpty(),
            species = input?.species.orEmpty(),
            gender = input?.gender.orEmpty(),
            location = input?.location?.name.orEmpty()
        )
    }
}