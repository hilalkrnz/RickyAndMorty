package com.example.feature.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.ui.model.CharacterUiData

internal class CharacterDiffCallBack: DiffUtil.ItemCallback<CharacterUiData>() {
    override fun areItemsTheSame(oldItem: CharacterUiData, newItem: CharacterUiData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterUiData, newItem: CharacterUiData): Boolean {
        return oldItem.id == newItem.id
    }
}