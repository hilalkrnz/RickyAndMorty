package com.example.rickyandmorty.data.api

import com.example.rickyandmorty.data.dto.Character
import com.example.rickyandmorty.data.dto.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int?,
        @Query("name") name: String?,
        @Query("status") status: String?
    ): CharacterResponse

    @GET("character/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: String): Character

}