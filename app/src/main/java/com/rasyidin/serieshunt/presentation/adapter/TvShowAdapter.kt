package com.rasyidin.serieshunt.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.ItemTvSquareBinding
import javax.inject.Inject

class TvShowAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<TvShow, ItemTvSquareBinding>(ItemTvSquareBinding::inflate, DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val tvShow = getItem(position)
        val binding = ItemTvSquareBinding.bind(holder.itemView)
        with(binding) {
            tvShow?.let {
                tvRating.text = it.voteAverage.toString()
                tvTitle.text = it.name

                if (it.backdropPath.isNullOrEmpty()) {
                    glide.load(R.drawable.ic_tv_placeholder)
                        .fitCenter()
                        .into(imgPoster)
                } else {
                    glide.load(BASE_URL_IMAGE + it.backdropPath)
                        .placeholder(R.drawable.ic_tv_placeholder)
                        .centerCrop()
                        .into(imgPoster)
                }

                root.setOnClickListener {
                    onItemClick?.invoke(tvShow, imgPoster, tvTitle)
                }

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