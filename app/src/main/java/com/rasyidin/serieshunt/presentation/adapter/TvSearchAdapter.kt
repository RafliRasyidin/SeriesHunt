package com.rasyidin.serieshunt.presentation.adapter

import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.ItemSearchBinding
import com.rasyidin.serieshunt.presentation.utils.toOnlyYearFormat
import javax.inject.Inject

class TvSearchAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<TvShow, ItemSearchBinding>(ItemSearchBinding::inflate, TvShowAdapter.DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val tvShow = getItem(position)
        val binding = ItemSearchBinding.bind(holder.itemView)
        with(binding) {
            val isPosterPathNullOrEmpty = tvShow.posterPath.isNullOrEmpty()
            if (isPosterPathNullOrEmpty) {
                glide.load(R.drawable.ic_tv_placeholder)
                    .fitCenter()
                    .into(imgPoster)
            } else {
                glide.load(BASE_URL_IMAGE + tvShow.posterPath)
                    .placeholder(R.drawable.ic_tv_placeholder)
                    .centerCrop()
                    .into(imgPoster)
            }

            tvTitle.text = tvShow.name
            tvRating.text = tvShow.voteAverage.toString()
            tvShow.firstAirDate?.let {
                tvDate.text = it.toOnlyYearFormat()
            }

            root.setOnClickListener {
                onItemClick?.invoke(tvShow, imgPoster, tvTitle)
            }
        }
    }

}