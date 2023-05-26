package com.example.feature.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.core.ui.databinding.AdapterCharacterItemBinding
import com.example.core.ui.CharacterUiData
import com.example.core.ui.utility.inflateAdapterItem
import javax.inject.Inject

class HomeRecyclerViewAdapter @Inject constructor():
    PagingDataAdapter<CharacterUiData, HomeViewHolder>(
        CharacterDiffCallBack()
    ) {

    private var onCharacterItemClickListener: ((CharacterUiData) -> Unit)? = null

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { characterUiData ->
            holder.onBind(characterUiData)
            holder.itemView.setOnClickListener {
                onCharacterItemClickListener?.invoke(characterUiData)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(parent.inflateAdapterItem(AdapterCharacterItemBinding::inflate))
    }

    fun setOnItemClickListener(listener: (CharacterUiData) -> Unit) {
        onCharacterItemClickListener = listener
    }

}

