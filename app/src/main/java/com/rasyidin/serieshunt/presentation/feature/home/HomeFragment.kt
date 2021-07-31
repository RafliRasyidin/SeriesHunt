package com.rasyidin.serieshunt.presentation.feature.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.databinding.FragmentHomeBinding
import com.rasyidin.serieshunt.presentation.adapter.OnTheAirAdapter
import com.rasyidin.serieshunt.presentation.adapter.PopularAdapter
import com.rasyidin.serieshunt.presentation.adapter.TopRatedAdapter
import com.rasyidin.serieshunt.presentation.adapter.TvShowAdapter
import com.rasyidin.serieshunt.presentation.base.BaseFragment
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
                            hideShimmerRvPotrait(
                                binding.contentContainer.rvPopular,
                                binding.contentContainer.shimmerRvPopular
                            )
                            popularAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> {
                    hideShimmerRvPotrait(
                        binding.contentContainer.rvPopular,
                        binding.contentContainer.shimmerRvPopular
                    )
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> showShimmerRvPotrait(
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
                            hideShimmerRvPotrait(
                                binding.contentContainer.rvOnTheAir,
                                binding.contentContainer.shimmerRvOnTheAir
                            )
                            onTheAirAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                    hideShimmerRvPotrait(
                        binding.contentContainer.rvOnTheAir,
                        binding.contentContainer.shimmerRvOnTheAir
                    )
                }
                is Resource.Loading -> showShimmerRvPotrait(
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
                            hideShimmerRvPotrait(
                                binding.contentContainer.rvTopRated,
                                binding.contentContainer.shimmerRvTopRated
                            )
                            topRatedAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                    hideShimmerRvPotrait(
                        binding.contentContainer.rvTopRated,
                        binding.contentContainer.shimmerRvTopRated
                    )
                }
                is Resource.Loading -> showShimmerRvPotrait(
                    binding.contentContainer.rvTopRated,
                    binding.contentContainer.shimmerRvTopRated
                )
            }
        }
    }

    private fun showShimmerRvSquare() {
        binding.apply {
            rvAiringToday.visibility = View.GONE
            shimmerRvAiringToday.visibility = View.VISIBLE
        }
    }

    private fun hideShimmerRvSquare() {
        binding.apply {
            rvAiringToday.visibility = View.VISIBLE
            shimmerRvAiringToday.visibility = View.GONE
        }
    }

    private fun showShimmerRvPotrait(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        recyclerView.visibility = View.GONE
        shimmer.visibility = View.VISIBLE
    }

    private fun hideShimmerRvPotrait(recyclerView: RecyclerView, shimmer: ShimmerFrameLayout) {
        recyclerView.visibility = View.VISIBLE
        shimmer.visibility = View.GONE
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