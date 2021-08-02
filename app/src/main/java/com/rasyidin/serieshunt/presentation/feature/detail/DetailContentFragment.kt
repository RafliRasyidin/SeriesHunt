package com.rasyidin.serieshunt.presentation.feature.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.FragmentDetailContentBinding
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailContentFragment :
    BaseFragment<FragmentDetailContentBinding>(FragmentDetailContentBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailContentFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    private var tvId: Int? = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            observeData()
        }

    }

    private fun observeData() {
        viewModel.getDetail(args.tvId).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    val tvShow = resource.data
                    binding.apply {
                        glide.load(BASE_URL_IMAGE + tvShow?.backdropPath)
                            .into(binding.toolbarContainer.imgBackdrop)

                        glide.load(BASE_URL_IMAGE + tvShow?.posterPath)
                            .into(binding.contentContainer.imgPoster)

                        toolbarContainer.tvTitle.text = tvShow?.name
                        contentContainer.apply {
                            tvDate.text = tvShow?.firstAirDate
                            tvRating.text = tvShow?.voteAverage.toString()
                            tvStatus.text = tvShow?.status
                        }
                    }
                }
                is Resource.Error -> Unit
                is Resource.Loading -> Unit
            }

        }

    }

    companion object {

        const val ARG_TV_ID = "tvId"

        fun newInstance(tvId: Int) =
            DetailContentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TV_ID, tvId)
                }
            }
    }

}