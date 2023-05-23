package com.example.rickyandmorty.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : Any, VH : BaseViewHolder<T>> :
    RecyclerView.Adapter<VH>() {

    private var onItemLongClickListener: ((T) -> Unit)? = null

    private val list = mutableListOf<T>()


    fun updateList(newList: List<T>) {
        list.apply {
            clear()
            addAll(newList)
            notifyItemInserted(newList.size)
            notifyItemRemoved(newList.size)
            // notifyDataSetChanged()
        }
    }

    fun getItem(position: Int) = list[position]

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.invoke(list[position])
            true
        }
    }

    override fun getItemCount() = list.size

    fun setOnItemLongClickListener(listener: (T) -> Unit) {
        onItemLongClickListener = listener
    }

}