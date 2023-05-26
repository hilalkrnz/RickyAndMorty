package com.example.feature.status.hate

import com.example.core.ui.base.BaseViewHolder
import com.example.core.ui.databinding.AdapterCharacterItemBinding
import com.example.core.ui.model.HateCharacter

class HateCharacterViewHolder(
    private val binding: AdapterCharacterItemBinding
) : BaseViewHolder<HateCharacter>(binding.root) {

    override fun onBind(data: HateCharacter) {
        binding.characterComponent.setHateCharacterData(data)
    }
}