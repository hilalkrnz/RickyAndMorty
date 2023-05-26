package com.example.feature.status.hate

import android.view.ViewGroup
import com.example.core.ui.base.BaseRecyclerViewAdapter
import com.example.core.ui.databinding.AdapterCharacterItemBinding
import com.example.core.ui.model.HateCharacter
import com.example.core.ui.utility.inflateAdapterItem

class HateCharacterRecyclerViewAdapter :
    BaseRecyclerViewAdapter<HateCharacter, HateCharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HateCharacterViewHolder {
        return HateCharacterViewHolder(
            parent.inflateAdapterItem(AdapterCharacterItemBinding::inflate)
        )
    }
}