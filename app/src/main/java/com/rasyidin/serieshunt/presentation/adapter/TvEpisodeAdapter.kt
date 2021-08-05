package com.rasyidin.serieshunt.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.TvEpisode
import com.rasyidin.serieshunt.databinding.ItemEpisodeBinding

class TvEpisodeAdapter :
    BaseAdapter<TvEpisode>(R.layout.item_episode, DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val tvEpisode = getItem(position)
        val binding = ItemEpisodeBinding.bind(holder.itemView)
        with(binding) {
            tvTitle.text = tvEpisode.name
            tvDate.text = tvEpisode.airDate
            tvNumberEpisode.text = tvEpisode.episodeNumber.toString()

            root.setOnClickListener {
                onItemClick?.invoke(tvEpisode)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<TvEpisode>() {
        override fun areItemsTheSame(oldItem: TvEpisode, newItem: TvEpisode): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvEpisode, newItem: TvEpisode): Boolean {
            return oldItem == newItem
        }
    }
}