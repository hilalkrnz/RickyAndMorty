package com.example.rickyandmorty.data.dto


import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val characters: List<Character>?
)