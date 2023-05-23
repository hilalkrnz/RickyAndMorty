package com.example.rickyandmorty.presentation.favorite.hate

import android.view.ViewGroup
import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.databinding.AdapterCharacterItemBinding
import com.example.rickyandmorty.presentation.base.BaseRecyclerViewAdapter
import com.example.rickyandmorty.utility.inflateAdapterItem

class HateCharacterRecyclerViewAdapter :
    BaseRecyclerViewAdapter<HateCharacter, HateCharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HateCharacterViewHolder {
        return HateCharacterViewHolder(
            parent.inflateAdapterItem(AdapterCharacterItemBinding::inflate)
        )
    }
}