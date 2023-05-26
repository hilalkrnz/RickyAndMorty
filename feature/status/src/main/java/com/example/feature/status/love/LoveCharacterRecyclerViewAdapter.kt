package com.example.feature.status.love

import android.view.ViewGroup
import com.example.core.ui.base.BaseRecyclerViewAdapter
import com.example.core.ui.databinding.AdapterCharacterItemBinding
import com.example.core.ui.model.LoveCharacter
import com.example.core.ui.utility.inflateAdapterItem

class LoveCharacterRecyclerViewAdapter :
    BaseRecyclerViewAdapter<LoveCharacter, LoveCharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoveCharacterViewHolder {
        return LoveCharacterViewHolder(
            parent.inflateAdapterItem(AdapterCharacterItemBinding::inflate)
        )
    }

}