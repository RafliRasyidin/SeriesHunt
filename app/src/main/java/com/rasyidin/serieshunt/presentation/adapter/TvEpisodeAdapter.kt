package com.rasyidin.serieshunt.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.TvEpisode
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.ItemEpisodeBinding
import javax.inject.Inject

class TvEpisodeAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<TvEpisode>(R.layout.item_episode, DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val tvEpisode = getItem(position)
        val binding = ItemEpisodeBinding.bind(holder.itemView)
        with(binding) {
            glide.load(BASE_URL_IMAGE + tvEpisode.posterPath).into(imgPoster)
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