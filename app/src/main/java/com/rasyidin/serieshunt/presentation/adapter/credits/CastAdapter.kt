package com.rasyidin.serieshunt.presentation.adapter.credits

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.Cast
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.ItemCreditsBinding
import com.rasyidin.serieshunt.presentation.adapter.BaseAdapter
import javax.inject.Inject

class CastAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<Cast>(R.layout.item_credits, DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cast = getItem(position)
        val binding = ItemCreditsBinding.bind(holder.itemView)
        with(binding) {
            if (cast.profilePath.isNullOrEmpty()) {
                glide.load(R.drawable.ic_star_placeholder)
                    .fitCenter()
                    .into(imgCredits)
            } else {
                glide.load(BASE_URL_IMAGE + cast.profilePath)
                    .placeholder(R.drawable.ic_star_placeholder)
                    .centerCrop()
                    .into(imgCredits)
            }
            tvName.text = cast.name
            tvCharacter.text = cast.character

            root.setOnClickListener {
                onItemClick?.invoke(cast)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }
}