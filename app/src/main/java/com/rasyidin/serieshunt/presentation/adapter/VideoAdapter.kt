package com.rasyidin.serieshunt.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.core.domain.model.VideoTrailer
import com.rasyidin.serieshunt.core.utils.Constants.BASE_YOUTUBE_THUMBNAIL_QUALITY_URL
import com.rasyidin.serieshunt.core.utils.Constants.BASE_YOUTUBE_THUMBNAIL_URL
import com.rasyidin.serieshunt.databinding.ItemTrailerBinding
import javax.inject.Inject

class VideoAdapter @Inject constructor(private val glide: RequestManager) :
    BaseAdapter<VideoTrailer, ItemTrailerBinding>(ItemTrailerBinding::inflate, DiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val video = getItem(position)
        val binding = ItemTrailerBinding.bind(holder.itemView)
        with(binding) {
            root.setOnClickListener {
                onItemClick?.invoke(video, imgVideo, null)
            }
            val url = BASE_YOUTUBE_THUMBNAIL_URL + video.key + BASE_YOUTUBE_THUMBNAIL_QUALITY_URL
            glide.load(url)
                .into(imgVideo)

            tvVideoType.text = video.type
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<VideoTrailer>() {
        override fun areItemsTheSame(oldItem: VideoTrailer, newItem: VideoTrailer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoTrailer, newItem: VideoTrailer): Boolean {
            return oldItem == newItem
        }
    }
}