package com.example.feature.home

import com.example.core.common.mapper.Mapper
import com.example.core.domain.model.CharacterDomainData
import com.example.core.ui.CharacterUiData
import javax.inject.Inject

class CharacterUiDataMapperImpl @Inject constructor() :
    Mapper<CharacterDomainData, CharacterUiData> {
    override fun map(input: CharacterDomainData?): CharacterUiData {
        return CharacterUiData(
            id = input?.id,
            image = input?.image.orEmpty(),
            name = input?.name.orEmpty(),
            status = input?.status.orEmpty(),
            species = input?.species.orEmpty(),
            gender = input?.gender.orEmpty(),
            location = input?.location?.orEmpty(),
        )
    }

}