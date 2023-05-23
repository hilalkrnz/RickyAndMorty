package com.example.rickyandmorty.presentation.favorite.hate

import androidx.recyclerview.widget.RecyclerView
import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.databinding.AdapterCharacterItemBinding
import com.example.rickyandmorty.presentation.base.BaseViewHolder

class HateCharacterViewHolder(
    private val binding: AdapterCharacterItemBinding
): BaseViewHolder<HateCharacter>(binding.root) {
    override fun onBind(data: HateCharacter) {
        binding.characterComponent.setHateCharacterData(data)
    }

}