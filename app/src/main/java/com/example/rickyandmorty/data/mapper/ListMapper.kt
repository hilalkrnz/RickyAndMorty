package com.example.rickyandmorty.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.data.dto.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun Flow<PagingData<Character>>.toCharacterUiDataDomain(list: List<CharacterUiData>): Flow<PagingData<CharacterUiData>> {
    return map{ pagingData ->
        pagingData.map { character ->
            CharacterUiData(
                id = character.id,
                name = character.name,
                image = character.image,
                species = character.species,
                status = character.status,
                gender = character.gender,
                origin = character.origin,
                location = character.location,
                episode = character.episode
            )
    }
    }
}