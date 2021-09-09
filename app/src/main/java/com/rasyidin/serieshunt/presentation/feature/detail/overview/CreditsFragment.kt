package com.rasyidin.serieshunt.presentation.feature.detail.overview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasyidin.serieshunt.core.utils.onFailure
import com.rasyidin.serieshunt.core.utils.onSuccess
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
        viewModel.getCast(tvId)
        viewModel.listCast.observe(viewLifecycleOwner) { resultState ->

            resultState.onSuccess { listCast ->
                if (listCast.isNotEmpty()) {
                    castAdapter.submitList(listCast)
                }
            }

            resultState.onFailure { throwable ->
                Toast.makeText(requireActivity(), "Something Wrong!", Toast.LENGTH_SHORT).show()
                Log.e(TAG, throwable.message.toString())
            }
        }
    }

    private fun observeCrew(tvId: Int) {
        viewModel.getCrew(tvId)
        viewModel.listCrew.observe(viewLifecycleOwner) { resultState ->

            resultState.onSuccess { listCrew ->
                if (listCrew.isNotEmpty()) {
                    crewAdapter.submitList(listCrew)
                }
            }

            resultState.onFailure { throwable ->
                Toast.makeText(requireActivity(), "Something Wrong!", Toast.LENGTH_SHORT).show()
                Log.e(TAG, throwable.message.toString())
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

    companion object {

        private const val ARG_TV_ID = "tvId"
        private const val ARG_SECTION_INDEX = "sectionIndex"
        private val TAG = CreditsFragment::class.simpleName

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