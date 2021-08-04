package com.rasyidin.serieshunt.presentation.adapter.credits

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.Crew
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.ItemCreditsBinding
import com.rasyidin.serieshunt.presentation.adapter.BaseAdapter
import javax.inject.Inject

class CrewAdapter @Inject constructor(private val glide: RequestManager): BaseAdapter<Crew>(R.layout.item_credits, DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val crew = getItem(position)
        val binding = ItemCreditsBinding.bind(holder.itemView)
        with(binding) {
            glide.load(BASE_URL_IMAGE + crew.profilePath).into(imgCredits)
            tvName.text = crew.name
            tvCharacter.text = crew.job

            root.setOnClickListener {
                onItemClick?.invoke(crew)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Crew>(){
        override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem == newItem
        }
    }
}