package com.example.feature.status.love

import com.example.core.ui.base.BaseViewHolder
import com.example.core.ui.databinding.AdapterCharacterItemBinding
import com.example.core.ui.model.LoveCharacter


class LoveCharacterViewHolder(
    private val binding: AdapterCharacterItemBinding
) : BaseViewHolder<LoveCharacter>(binding.root) {

    override fun onBind(data: LoveCharacter) {
        binding.characterComponent.setLoveCharacterData(data)
    }
}