package com.rasyidin.serieshunt.presentation.feature.detail.trailers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.utils.Constants.BASE_YOUTUBE_URL
import com.rasyidin.serieshunt.databinding.FragmentTrailersBinding
import com.rasyidin.serieshunt.presentation.adapter.VideoAdapter
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import com.rasyidin.serieshunt.presentation.feature.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrailersFragment : BaseFragment<FragmentTrailersBinding>(FragmentTrailersBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var videoAdapter: VideoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()
        arguments?.getInt(ARG_ID_TV)?.let { tvId ->
            observeVideos(tvId)
        }

        videoAdapter.onItemClick = { videoTrailer ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BASE_YOUTUBE_URL + videoTrailer.key))
            startActivity(intent)
        }

    }

    private fun observeVideos(tvId: Int) {
        viewModel.getVideos(tvId).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> Unit
                is Resource.Loading -> Unit
                is Resource.Success -> videoAdapter.submitList(resource.data)
            }
        }
    }

    private fun setupRv() = binding.rvTrailer.apply {
        adapter = videoAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {

        private const val ARG_ID_TV = "idTv"

        @JvmStatic
        fun newInstance(idTv: Int) =
            TrailersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_TV, idTv)
                }
            }
    }
}