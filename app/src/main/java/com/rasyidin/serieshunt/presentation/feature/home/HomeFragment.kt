package com.rasyidin.serieshunt.presentation.feature.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.databinding.FragmentHomeBinding
import com.rasyidin.serieshunt.presentation.adapter.OnTheAirAdapter
import com.rasyidin.serieshunt.presentation.adapter.PopularAdapter
import com.rasyidin.serieshunt.presentation.adapter.TopRatedAdapter
import com.rasyidin.serieshunt.presentation.adapter.TvShowAdapter
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import com.rasyidin.serieshunt.presentation.feature.detail.DetailContentFragment.Companion.ARG_OVERVIEW
import com.rasyidin.serieshunt.presentation.feature.detail.DetailContentFragment.Companion.ARG_TV_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var airingTodayAdapter: TvShowAdapter

    @Inject
    lateinit var popularAdapter: PopularAdapter

    @Inject
    lateinit var onTheAirAdapter: OnTheAirAdapter

    @Inject
    lateinit var topRatedAdapter: TopRatedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvAiringToday()

        setupRvOnTheAir()

        setupRvPopular()

        setupRvTopRated()

        subscribeToObserver()

        navigateToDetail()

    }

    private fun subscribeToObserver() {
        homeViewModel.getAiringToday.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listTvShow ->
                        if (listTvShow.isNotEmpty()) {
                            hideShimmerRvSquare()
                            airingTodayAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                    hideShimmerRvSquare()
                }
                is Resource.Loading -> showShimmerRvSquare()
            }
        }

        homeViewModel.getPopular.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listTvShow ->
                        if (listTvShow.isNotEmpty()) {
                            hideShimmerRvPortrait(
                                binding.contentContainer.rvPopular,
                                binding.contentContainer.shimmerRvPopular
                            )
                            popularAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> {
                    hideShimmerRvPortrait(
                        binding.contentContainer.rvPopular,
                        binding.contentContainer.shimmerRvPopular
                    )
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> showShimmerRvPortrait(
                    binding.contentContainer.rvPopular,
                    binding.contentContainer.shimmerRvPopular
                )
            }
        }

        homeViewModel.getOnTheAir.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listTvShow ->
                        if (listTvShow.isNotEmpty()) {
                            hideShimmerRvPortrait(
                                binding.contentContainer.rvOnTheAir,
                                binding.contentContainer.shimmerRvOnTheAir
                            )
                            onTheAirAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                    hideShimmerRvPortrait(
                        binding.contentContainer.rvOnTheAir,
                        binding.contentContainer.shimmerRvOnTheAir
                    )
                }
                is Resource.Loading -> showShimmerRvPortrait(
                    binding.contentContainer.rvOnTheAir,
                    binding.contentContainer.shimmerRvOnTheAir
                )
            }
        }

        homeViewModel.getTopRated.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listTvShow ->
                        if (listTvShow.isNotEmpty()) {
                            hideShimmerRvPortrait(
                                binding.contentContainer.rvTopRated,
                                binding.contentContainer.shimmerRvTopRated
                            )
                            topRatedAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                    hideShimmerRvPortrait(
                        binding.contentContainer.rvTopRated,
                        binding.contentContainer.shimmerRvTopRated
                    )
                }
                is Resource.Loading -> showShimmerRvPortrait(
                    binding.contentContainer.rvTopRated,
                    binding.contentContainer.shimmerRvTopRated
                )
            }
        }
    }

    private fun navigateToDetail() {
        var args: Bundle
        popularAdapter.onItemClick = { tvShow ->
            args = Bundle().apply {
                putInt(ARG_TV_ID, tvShow.id)
                putString(ARG_OVERVIEW, tvShow.overview)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailContentFragment, args)
        }

        topRatedAdapter.onItemClick = { tvShow ->
            args = Bundle().apply {
                putInt(ARG_TV_ID, tvShow.id)
                putString(ARG_OVERVIEW, tvShow.overview)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailContentFragment, args)
        }

        onTheAirAdapter.onItemClick = { tvShow ->
            args = Bundle().apply {
                putInt(ARG_TV_ID, tvShow.id)
                putString(ARG_OVERVIEW, tvShow.overview)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailContentFragment, args)
        }

        airingTodayAdapter.onItemClick = { tvShow ->
            args = Bundle().apply {
                putInt(ARG_TV_ID, tvShow.id)
                putString(ARG_OVERVIEW, tvShow.overview)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailContentFragment, args)
        }
    }

    private fun showShimmerRvSquare() {
        binding.apply {
            rvAiringToday.visibility = View.INVISIBLE
            shimmerRvAiringToday.visibility = View.VISIBLE
        }
    }

    private fun hideShimmerRvSquare() {
        binding.apply {
            rvAiringToday.visibility = View.VISIBLE
            shimmerRvAiringToday.visibility = View.INVISIBLE
        }
    }

    private fun showShimmerRvPortrait(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        recyclerView.visibility = View.INVISIBLE
        shimmer.visibility = View.VISIBLE
    }

    private fun hideShimmerRvPortrait(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        recyclerView.visibility = View.VISIBLE
        shimmer.visibility = View.INVISIBLE
    }

    private fun setupRvAiringToday() = binding.rvAiringToday.apply {
        adapter = airingTodayAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(this)
    }

    private fun setupRvPopular() = binding.contentContainer.rvPopular.apply {
        adapter = popularAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupRvOnTheAir() = binding.contentContainer.rvOnTheAir.apply {
        adapter = onTheAirAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupRvTopRated() = binding.contentContainer.rvTopRated.apply {
        adapter = topRatedAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

}