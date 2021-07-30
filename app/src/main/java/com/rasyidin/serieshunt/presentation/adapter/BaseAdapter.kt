package com.rasyidin.serieshunt.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    @LayoutRes private val layoutId: Int,
    diffCallback: DiffUtil.ItemCallback<T>
) :
    ListAdapter<T, BaseAdapter.ItemViewHolder>(diffCallback) {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var onItemClick: ((T) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
    }

}