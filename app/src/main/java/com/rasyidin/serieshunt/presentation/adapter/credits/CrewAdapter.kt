package com.rasyidin.serieshunt.presentation.adapter.credits

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.Crew
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.ItemCreditsBinding
import com.rasyidin.serieshunt.presentation.adapter.BaseAdapter
import javax.inject.Inject

class CrewAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<Crew, ItemCreditsBinding>(ItemCreditsBinding::inflate, DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val crew = getItem(position)
        val binding = ItemCreditsBinding.bind(holder.itemView)
        with(binding) {

            if (crew.profilePath.isNullOrEmpty()) {
                glide.load(R.drawable.ic_star_placeholder)
                    .fitCenter()
                    .into(imgCredits)
            } else {
                glide.load(BASE_URL_IMAGE + crew.profilePath)
                    .placeholder(R.drawable.ic_star_placeholder)
                    .centerCrop()
                    .into(imgCredits)
            }

            tvName.text = crew.name
            tvCharacter.text = crew.job

            root.setOnClickListener {
                onItemClick?.invoke(crew, imgCredits, tvName)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Crew>() {
        override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem == newItem
        }
    }
}