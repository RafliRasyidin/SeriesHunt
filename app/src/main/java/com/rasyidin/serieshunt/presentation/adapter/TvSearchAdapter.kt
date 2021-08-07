package com.rasyidin.serieshunt.presentation.adapter

import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.ItemSearchBinding
import com.rasyidin.serieshunt.presentation.utils.toOnlyYearFormat
import javax.inject.Inject

class TvSearchAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<TvShow>(R.layout.item_search, TvShowAdapter.DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val tvShow = getItem(position)
        val binding = ItemSearchBinding.bind(holder.itemView)
        with(binding) {
            glide.load(BASE_URL_IMAGE + tvShow.posterPath)
                .placeholder(R.drawable.ic_tv_placeholder)
                .into(imgPoster)
            tvTitle.text = tvShow.name
            tvRating.text = tvShow.voteAverage.toString()
            tvShow.firstAirDate?.let {
                if (it.isEmpty()) {
                    tvDate.text = tvShow.firstAirDate
                } else {
                    tvDate.text = tvShow.firstAirDate.toOnlyYearFormat()
                }
            }

            root.setOnClickListener {
                onItemClick?.invoke(tvShow)
            }
        }
    }

}