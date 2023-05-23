package com.example.rickyandmorty.presentation.home.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickyandmorty.databinding.AdapterCharacterItemBinding
import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.utility.inflateAdapterItem
import javax.inject.Inject

class HomeRecyclerViewAdapter @Inject constructor():
    PagingDataAdapter<CharacterUiData, HomeViewHolder>(CharacterDiffCallBack()) {

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
        val holder = HomeViewHolder(parent.inflateAdapterItem(AdapterCharacterItemBinding::inflate))
        return holder
    }

    fun setOnItemClickListener(listener: (CharacterUiData) -> Unit) {
        onCharacterItemClickListener = listener
    }

}

