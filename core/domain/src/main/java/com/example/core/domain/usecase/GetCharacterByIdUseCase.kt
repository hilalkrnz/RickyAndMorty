package com.example.core.domain.usecase

import com.example.core.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    operator fun invoke(characterId: String) = rickAndMortyRepository.getCharacterById(characterId)
}