package com.example.core.domain.usecase

import com.example.core.common.utils.NameState
import com.example.core.common.utils.StatusState
import com.example.core.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    operator fun invoke(name: NameState, status: StatusState) = rickAndMortyRepository.getAllCharacters(name, status)
}