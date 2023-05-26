package com.example.feature.home.adapter

import com.example.core.ui.base.BaseViewHolder
import com.example.core.ui.databinding.AdapterCharacterItemBinding
import com.example.core.ui.CharacterUiData


class HomeViewHolder(
    private val binding: AdapterCharacterItemBinding
) : BaseViewHolder<CharacterUiData>(binding.root) {

    override fun onBind(data: CharacterUiData) {
        binding.characterComponent.setCharacterData(data)
    }

}
