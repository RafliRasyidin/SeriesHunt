package com.rasyidin.serieshunt.presentation.feature.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.databinding.FragmentSearchBinding
import com.rasyidin.serieshunt.presentation.adapter.TvSearchAdapter
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import com.rasyidin.serieshunt.presentation.feature.detail.DetailContentFragment.Companion.ARG_OVERVIEW
import com.rasyidin.serieshunt.presentation.feature.detail.DetailContentFragment.Companion.ARG_TV_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@ObsoleteCoroutinesApi
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    
    private val viewModel: SearchViewModel by viewModels()
    
    @Inject
    lateinit var tvSearchAdapter: TvSearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRv()
        
        searchTvShow()

        observeSearchTv()

        navigateToDetail()
    }

    private fun navigateToDetail() {
        tvSearchAdapter.onItemClick = { tvShow ->
            val args = Bundle().apply {
                putInt(ARG_TV_ID, tvShow.id)
                putString(ARG_OVERVIEW, tvShow.overview)
            }
            findNavController().navigate(R.id.action_searchFragment_to_detailContentFragment, args)
        }
    }

    private fun searchTvShow() {
        binding.svTv.onActionViewExpanded()
        binding.svTv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                lifecycleScope.launch {
                    viewModel.queryChannel.send(newText)
                }
                return true
            }
        })
    }

    private fun observeSearchTv() {
        viewModel.searchTvShow.observe(viewLifecycleOwner) { tvShow ->
            tvShow.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Error -> Toast.makeText(activity, "Something Wrong!", Toast.LENGTH_SHORT).show()
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        val data = resource.data
                        data?.let {
                            if (data.isNotEmpty()) {
                                tvSearchAdapter.submitList(data)
                            } else {
                                Toast.makeText(activity, "Not Found!", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                }
            }

        }
    }

    private fun setupRv() = binding.rvSearch.apply { 
        adapter = tvSearchAdapter
        layoutManager = LinearLayoutManager(activity)
    }

}