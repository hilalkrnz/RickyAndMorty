package com.example.rickyandmorty.presentation.favorite.love

import com.example.rickyandmorty.data.database.FavoriteCharacter
import com.example.rickyandmorty.databinding.AdapterCharacterItemBinding
import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.presentation.base.BaseViewHolder

class LoveCharacterViewHolder(
    private val binding: AdapterCharacterItemBinding
) : BaseViewHolder<FavoriteCharacter>(binding.root) {

    override fun onBind(data: FavoriteCharacter) {
        binding.characterComponent.setFavoriteCharacterData(data)
    }
}