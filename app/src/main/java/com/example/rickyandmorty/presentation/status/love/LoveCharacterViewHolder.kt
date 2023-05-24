package com.example.rickyandmorty.presentation.status.love

import com.example.rickyandmorty.data.database.LoveCharacter
import com.example.rickyandmorty.databinding.AdapterCharacterItemBinding
import com.example.rickyandmorty.presentation.base.BaseViewHolder

class LoveCharacterViewHolder(
    private val binding: AdapterCharacterItemBinding
) : BaseViewHolder<LoveCharacter>(binding.root) {

    override fun onBind(data: LoveCharacter) {
        binding.characterComponent.setLoveCharacterData(data)
    }
}