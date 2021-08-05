package com.rasyidin.serieshunt.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.ItemTvPortraitBinding
import javax.inject.Inject

open class PopularAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<TvShow>(R.layout.item_tv_portrait, DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val tvShow = getItem(position)
        val binding = ItemTvPortraitBinding.bind(holder.itemView)
        with(binding) {
            ratingContainer.tvRating.text = tvShow.voteAverage.toString()

            if (tvShow.backdropPath.isNullOrEmpty()) {
                glide.load(R.drawable.ic_tv_placeholder).into(imgFilm)
            } else {
                glide.load(BASE_URL_IMAGE + tvShow.backdropPath)
                    .placeholder(R.drawable.ic_tv_placeholder)
                    .centerCrop()
                    .into(imgFilm)
            }

            root.setOnClickListener {
                onItemClick?.invoke(tvShow)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
        }
    }
}

class OnTheAirAdapter @Inject constructor(glide: RequestManager) : PopularAdapter(glide)

class TopRatedAdapter @Inject constructor(glide: RequestManager) : PopularAdapter(glide)