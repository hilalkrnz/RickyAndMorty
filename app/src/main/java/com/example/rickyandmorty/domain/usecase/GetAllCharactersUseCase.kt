package com.example.rickyandmorty.domain.usecase

import com.example.rickyandmorty.domain.repository.RickAndMortyRepository
import com.example.rickyandmorty.utility.NameState
import com.example.rickyandmorty.utility.StatusState
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend operator fun invoke(name: NameState, status: StatusState) = rickAndMortyRepository.getAllCharacters(name, status)
}