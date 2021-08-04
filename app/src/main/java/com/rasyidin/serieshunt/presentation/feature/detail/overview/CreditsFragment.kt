package com.rasyidin.serieshunt.presentation.feature.detail.overview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.databinding.FragmentCreditsBinding
import com.rasyidin.serieshunt.presentation.adapter.credits.CastAdapter
import com.rasyidin.serieshunt.presentation.adapter.credits.CrewAdapter
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import com.rasyidin.serieshunt.presentation.feature.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreditsFragment : BaseFragment<FragmentCreditsBinding>(FragmentCreditsBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var castAdapter: CastAdapter

    @Inject
    lateinit var crewAdapter: CrewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvId = arguments?.getInt(ARG_TV_ID)

        tvId?.let {
            setupSectionIndexVp(it)
        }

    }

    private fun observeCast(tvId: Int) {
        viewModel.getCast(tvId).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> Toast.makeText(activity, "Something Wrong", Toast.LENGTH_SHORT)
                    .show()
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    val listCast = resource.data
                    listCast?.let {
                        if (it.isNotEmpty()) {
                            castAdapter.submitList(it)
                        }
                    }

                }
            }
        }
    }

    private fun setupRvCast() = binding.rvCredits.apply {
        adapter = castAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupRvCrew() = binding.rvCredits.apply {
        adapter = crewAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupSectionIndexVp(tvId: Int) {
        var index: Int? = 0
        if (arguments != null) {
            index = arguments?.getInt(ARG_SECTION_INDEX, 0)
        }
        when (index) {
            0 -> {
                setupRvCast()
                observeCast(tvId)
            }
            1 -> {
                setupRvCrew()
                observeCrew(tvId)
            }
        }
    }

    private fun observeCrew(tvId: Int) {
        viewModel.getCrew(tvId).observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> Toast.makeText(activity, "Something Wrong", Toast.LENGTH_SHORT)
                    .show()
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    val listCast = resource.data
                    listCast?.let {
                        if (it.isNotEmpty()) {
                            crewAdapter.submitList(it)
                        }
                    }

                }
            }
        }
    }

    companion object {

        private const val ARG_TV_ID = "tvId"
        private const val ARG_SECTION_INDEX = "sectionIndex"

        @JvmStatic
        fun newInstance(tvId: Int, index: Int) =
            CreditsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TV_ID, tvId)
                    putInt(ARG_SECTION_INDEX, index)
                }
            }
    }
}