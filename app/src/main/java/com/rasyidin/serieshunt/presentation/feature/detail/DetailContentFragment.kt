package com.rasyidin.serieshunt.presentation.feature.detail

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.utils.Constants.BASE_URL_IMAGE
import com.rasyidin.serieshunt.databinding.FragmentDetailContentBinding
import com.rasyidin.serieshunt.presentation.adapter.DetailPagerAdapter
import com.rasyidin.serieshunt.presentation.adapter.DetailPagerAdapter.Companion.TAB_TITLES
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import com.rasyidin.serieshunt.presentation.utils.toOnlyYearFormat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailContentFragment :
    BaseFragment<FragmentDetailContentBinding>(FragmentDetailContentBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailContentFragmentArgs by navArgs()

    private var tvShow: TvShow? = null

    @Inject
    lateinit var glide: RequestManager

    private lateinit var mediator: TabLayoutMediator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            observeData()

            shareTvShow()

            binding.toolbarContainer.imgBack.setOnClickListener {
                findNavController().navigate(R.id.action_detailContentFragment_to_homeFragment)
            }
        }

    }

    private fun shareTvShow() {
        binding.toolbarContainer.imgShare.setOnClickListener {
            val data = StringBuilder()
            data.apply {
                append(tvShow?.name)
                append("\n\n")
                append(tvShow?.homepage)
                append("\n\n")
                append(tvShow?.overview)
            }
            val sendIntent = Intent().apply {
                action = ACTION_SEND
                type = "text/plain"
                putExtra(EXTRA_TEXT, data.toString())
            }
            val shareIntent = createChooser(sendIntent, tvShow?.name)
            startActivity(shareIntent)
        }
    }

    private fun observeData() {
        viewModel.getDetail(args.tvId).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    binding.pbDetail.visibility = View.GONE
                    tvShow = resource.data
                    tvShow?.let {
                        initViewPager(it.id, it.overview, it.numberOfSeasons)
                        initTabLayout()

                        binding.apply {
                            val isBackdropNull = it.backdropPath.isNullOrEmpty()
                            if (isBackdropNull) {
                                glide.load(R.drawable.ic_tv_placeholder)
                                    .into(toolbarContainer.imgBackdrop)
                            } else {
                                glide.load(BASE_URL_IMAGE + it.backdropPath)
                                    .placeholder(R.drawable.ic_tv_placeholder)
                                    .into(toolbarContainer.imgBackdrop)
                            }

                            val isPosterNull = it.posterPath.isNullOrEmpty()
                            if (isPosterNull) {
                                glide.load(R.drawable.ic_tv_placeholder)
                                    .into(contentContainer.imgPoster)
                            } else {
                                glide.load(BASE_URL_IMAGE + it.posterPath)
                                    .placeholder(R.drawable.ic_tv_placeholder)
                                    .into(contentContainer.imgPoster)
                            }

                            toolbarContainer.tvTitle.text = it.name
                            contentContainer.apply {
                                tvDate.text = it.firstAirDate?.toOnlyYearFormat()
                                tvRating.text = it.voteAverage.toString()
                                tvStatus.text = it.status
                                for (index in it.genres.indices) {
                                    val chip = Chip(chipGroup.context)
                                    chip.text = it.genres[index].name
                                    chip.isCheckable = false
                                    chip.isCheckable = false
                                    chipGroup.addView(chip)
                                }
                            }
                        }
                    }


                }
                is Resource.Error -> {
                    binding.pbDetail.visibility = View.GONE
                    Toast.makeText(activity, "Something Wrong!", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> binding.pbDetail.visibility = View.VISIBLE
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