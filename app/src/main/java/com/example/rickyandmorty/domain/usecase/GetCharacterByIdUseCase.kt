package com.example.rickyandmorty.domain.usecase

import com.example.rickyandmorty.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend operator fun invoke(characterId: String) =
        rickAndMortyRepository.getCharacterById(characterId)
}