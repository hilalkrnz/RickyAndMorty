package com.example.rickyandmorty.presentation.home.adapter

import com.example.rickyandmorty.databinding.AdapterCharacterItemBinding
import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.presentation.base.BaseViewHolder

class HomeViewHolder(
    val binding: AdapterCharacterItemBinding
) : BaseViewHolder<CharacterUiData>(binding.root) {

    override fun onBind(data: CharacterUiData) {
        binding.characterComponent.setCharacterData(data)
    }

}
