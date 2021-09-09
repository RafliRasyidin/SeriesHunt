package com.rasyidin.serieshunt.presentation.feature.detail.trailers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.serieshunt.core.utils.Constants.BASE_YOUTUBE_URL
import com.rasyidin.serieshunt.core.utils.onFailure
import com.rasyidin.serieshunt.core.utils.onSuccess
import com.rasyidin.serieshunt.databinding.FragmentTrailersBinding
import com.rasyidin.serieshunt.presentation.adapter.VideoAdapter
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import com.rasyidin.serieshunt.presentation.feature.detail.DetailViewModel
import com.rasyidin.serieshunt.presentation.utils.isSuccess
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

        videoAdapter.onItemClick = { videoTrailer, _, _ ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BASE_YOUTUBE_URL + videoTrailer.key))
            startActivity(intent)
        }

    }

    private fun observeVideos(tvId: Int) {
        viewModel.getVideos(tvId)
        viewModel.videos.observe(viewLifecycleOwner) { resultState ->

            binding.run {

                rvTrailer.isVisible = isSuccess(resultState)
                resultState.onSuccess { videos ->
                    if (videos.isNotEmpty()) {
                        videoAdapter.submitList(videos)
                        rvTrailer.isVisible = true
                        lottieVideo.isVisible = false
                        tvEmpty.isVisible = false
                    } else {
                        rvTrailer.isVisible = false
                        lottieVideo.isVisible = true
                        tvEmpty.isVisible = true
                    }
                }

                resultState.onFailure { throwable ->
                    Toast.makeText(requireActivity(), "Something Wrong!", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, throwable.message.toString())
                }
            }

        }
    }

    private fun setupRv() = binding.rvTrailer.apply {
        adapter = videoAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    companion object {

        private const val ARG_ID_TV = "idTv"
        private val TAG = TrailersFragment::class.simpleName

        @JvmStatic
        fun newInstance(idTv: Int) =
            TrailersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_TV, idTv)
                }
            }
    }
}