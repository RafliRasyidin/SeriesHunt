package com.rasyidin.serieshunt.presentation.feature.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
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
                            airingTodayAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                is Resource.Loading -> Unit
            }
        }

        homeViewModel.getPopular.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listTvShow ->
                        if (listTvShow.isNotEmpty()) {
                            popularAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                is Resource.Loading -> Unit
            }
        }

        homeViewModel.getOnTheAir.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listTvShow ->
                        if (listTvShow.isNotEmpty()) {
                            onTheAirAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                is Resource.Loading -> Unit
            }
        }

        homeViewModel.getTopRated.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { listTvShow ->
                        if (listTvShow.isNotEmpty()) {
                            topRatedAdapter.submitList(listTvShow)
                        }
                    }
                }
                is Resource.Error -> Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                is Resource.Loading -> Unit
            }
        }
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