package com.rasyidin.serieshunt.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.ItemTvPotraitBinding
import javax.inject.Inject

open class PopularAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<TvShow>(R.layout.item_tv_potrait, DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val tvShow = getItem(position)
        val binding = ItemTvPotraitBinding.bind(holder.itemView)
        with(binding) {
            ratingContainer.tvRating.text = tvShow.voteAverage.toString()
            glide.load(BASE_URL_IMAGE + tvShow.posterPath)
                .centerCrop()
                .into(imgFilm)
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