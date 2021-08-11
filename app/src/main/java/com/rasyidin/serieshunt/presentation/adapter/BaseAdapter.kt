package com.rasyidin.serieshunt.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
abstract class BaseAdapter<T, VB: ViewBinding>(
    private val inflate: Inflate<VB>,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseAdapter.ItemViewHolder>(diffCallback) {

    class ItemViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    var onItemClick: ((T, view1: View?, view2: View?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(inflate.invoke(LayoutInflater.from(parent.context), parent, false))
    }



}