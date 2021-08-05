package com.rasyidin.serieshunt.presentation.feature.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.FragmentDetailContentBinding
import com.rasyidin.serieshunt.presentation.adapter.DetailPagerAdapter
import com.rasyidin.serieshunt.presentation.adapter.DetailPagerAdapter.Companion.TAB_TITLES
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import com.rasyidin.serieshunt.presentation.utils.toYearFormat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailContentFragment :
    BaseFragment<FragmentDetailContentBinding>(FragmentDetailContentBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailContentFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    private lateinit var mediator: TabLayoutMediator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            observeData()

            binding.toolbarContainer.imgBack.setOnClickListener {
                findNavController().navigate(R.id.action_detailContentFragment_to_homeFragment)
            }
        }

    }

    private fun observeData() {
        viewModel.getDetail(args.tvId).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    val tvShow = resource.data
                    tvShow?.let {
                        initViewPager(it.id, it.overview, it.numberOfSeasons)
                    }
                    initTabLayout()

                    binding.apply {
                        glide.load(BASE_URL_IMAGE + tvShow?.backdropPath)
                            .into(binding.toolbarContainer.imgBackdrop)

                        glide.load(BASE_URL_IMAGE + tvShow?.posterPath)
                            .into(binding.contentContainer.imgPoster)

                        toolbarContainer.tvTitle.text = tvShow?.name
                        contentContainer.apply {
                            tvDate.text = tvShow?.firstAirDate?.toYearFormat()
                            tvRating.text = tvShow?.voteAverage.toString()
                            tvStatus.text = tvShow?.status
                            for (index in tvShow!!.genres.indices) {
                                val chip = Chip(chipGroup.context)
                                chip.text = tvShow.genres[index].name
                                chip.isCheckable = false
                                chip.isCheckable = false
                                chipGroup.addView(chip)
                            }
                        }

                    }
                }
                is Resource.Error -> Unit
                is Resource.Loading -> Unit
            }

        }

    }

    private fun initViewPager(tvId: Int, overview: String?, seasonOfNumber: Int) {
        val detailPagerAdapter: DetailPagerAdapter by lazy {
            DetailPagerAdapter(childFragmentManager, lifecycle, tvId, overview, seasonOfNumber)
        }
        binding.vpContainer.vp.apply {
            isUserInputEnabled = false
            adapter = detailPagerAdapter
            offscreenPageLimit = 3
        }
    }

    private fun initTabLayout() {
        mediator = TabLayoutMediator(binding.vpContainer.tabs, binding.vpContainer.vp) { tab, pos ->
            tab.text = when (pos) {
                0 -> getString(TAB_TITLES[0])
                1 -> getString(TAB_TITLES[1])
                else -> getString(TAB_TITLES[2])
            }
        }
        mediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediator.detach()
    }

    companion object {

        const val ARG_TV_ID = "tvId"

        const val ARG_OVERVIEW = "overview"

        fun newInstance(tvId: Int, overview: String?) =
            DetailContentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TV_ID, tvId)
                    putString(ARG_OVERVIEW, overview)
                }
            }
    }

}