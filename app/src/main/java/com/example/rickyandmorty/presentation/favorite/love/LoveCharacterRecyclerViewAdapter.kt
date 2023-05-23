package com.example.rickyandmorty.presentation.favorite.love

import android.view.ViewGroup
import com.example.rickyandmorty.data.database.FavoriteCharacter
import com.example.rickyandmorty.databinding.AdapterCharacterItemBinding
import com.example.rickyandmorty.presentation.base.BaseRecyclerViewAdapter
import com.example.rickyandmorty.utility.inflateAdapterItem

class LoveCharacterRecyclerViewAdapter :
    BaseRecyclerViewAdapter<FavoriteCharacter, LoveCharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoveCharacterViewHolder {
        return LoveCharacterViewHolder(
            parent.inflateAdapterItem(AdapterCharacterItemBinding::inflate)
        )
    }

}