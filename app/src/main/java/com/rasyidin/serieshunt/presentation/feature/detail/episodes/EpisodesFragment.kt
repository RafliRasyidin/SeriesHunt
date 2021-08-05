package com.rasyidin.serieshunt.presentation.feature.detail.episodes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.databinding.FragmentEpisodesBinding
import com.rasyidin.serieshunt.presentation.adapter.TvEpisodeAdapter
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import com.rasyidin.serieshunt.presentation.feature.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding>(FragmentEpisodesBinding::inflate),
    AdapterView.OnItemSelectedListener {

    private val viewModel: DetailViewModel by viewModels()

    private val tvEpisodeAdapter: TvEpisodeAdapter by lazy { TvEpisodeAdapter() }

    private var seasonNumber = 1
    private var tvId = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvId = arguments?.getInt(ARG_ID_TV)!!
        val numberOfSeason = arguments?.getInt(ARG_NUMBER_OF_SEASON)

        setupRv()

        setupSpinnerAdapter(tvId, numberOfSeason!!)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        seasonNumber = position + 1
        observeTvSeasons(tvId, seasonNumber)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun observeTvSeasons(tvId: Int, seasonNumber: Int) {
        viewModel.getTvSeason(tvId, seasonNumber).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    Toast.makeText(
                        activity,
                        "Something Wrong!",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("EpisodesFragment", "${resource.message}")
                }
                is Resource.Loading -> Unit
                is Resource.Success -> tvEpisodeAdapter.submitList(resource.data)
            }
        }
    }

    private fun setupSpinnerAdapter(tvId: Int, numberOfSeason: Int) {
        var indexSeason = 1
        val options = ArrayList<String>()
        for (i in 1..numberOfSeason) {
            options.add("Season $indexSeason")
            indexSeason++
        }

        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, options)

        binding.spinnerSeasons.adapter = adapter
        binding.spinnerSeasons.onItemSelectedListener = this

        observeTvSeasons(tvId, seasonNumber)
    }

    private fun setupRv() = binding.rvEpisode.apply {
        adapter = tvEpisodeAdapter
        layoutManager = LinearLayoutManager(activity)
    }

    companion object {

        private const val ARG_ID_TV = "idTv"
        private const val ARG_NUMBER_OF_SEASON = "numberOfSeason"

        @JvmStatic
        fun newInstance(idTv: Int, numberOfSeason: Int) =
            EpisodesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_TV, idTv)
                    putInt(ARG_NUMBER_OF_SEASON, numberOfSeason)
                }
            }
    }
}