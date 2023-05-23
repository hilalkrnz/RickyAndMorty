package com.example.rickyandmorty.data.repository

import com.example.rickyandmorty.data.database.FavoriteCharacter
import com.example.rickyandmorty.data.database.FavoriteCharacterDao
import com.example.rickyandmorty.domain.repository.FavoriteCharacterRepository
import javax.inject.Inject

class FavoriteCharacterRepositoryImpl @Inject constructor(
    private val favoriteCharacterDao: FavoriteCharacterDao
) : FavoriteCharacterRepository {
    override suspend fun addToFavorite(favoriteCharacter: FavoriteCharacter) =
        favoriteCharacterDao.addToFavorite(favoriteCharacter)

    override fun getFavoriteCharacters() = favoriteCharacterDao.getFavoriteCharacters()

    override suspend fun checkFavoriteCharacter(id: String) =
        favoriteCharacterDao.checkFavoriteCharacter(id)

    override suspend fun removeFromFavorite(id: String) =
        favoriteCharacterDao.removeFromFavorite(id)
}