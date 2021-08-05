package com.rasyidin.serieshunt.presentation.feature.detail.overview

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.databinding.FragmentOverviewBinding
import com.rasyidin.serieshunt.presentation.adapter.credits.CreditsPagerAdapter
import com.rasyidin.serieshunt.presentation.adapter.credits.CreditsPagerAdapter.Companion.TAB_TITLES
import com.rasyidin.serieshunt.presentation.base.BaseFragment

class OverviewFragment : BaseFragment<FragmentOverviewBinding>(FragmentOverviewBinding::inflate) {

    private lateinit var mediator: TabLayoutMediator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val overview = arguments?.getString(ARG_OVERVIEW)
        val tvId = arguments?.getInt(ARG_ID_TV)
        if (overview.isNullOrEmpty()) {
            binding.tvOverview.text = getString(R.string.empty_overview)
        } else {
            binding.tvOverview.text = overview
        }

        tvId?.let {
            initViewPager(it)
        }
        initTabLayout()

    }

    private fun initViewPager(tvId: Int) {
        val pagerAdapter: CreditsPagerAdapter by lazy {
            CreditsPagerAdapter(childFragmentManager, lifecycle, tvId)
        }
        binding.vp.apply {
            isUserInputEnabled = false
            adapter = pagerAdapter
            offscreenPageLimit = 2
        }
    }

    private fun initTabLayout() {
        mediator = TabLayoutMediator(binding.tabs, binding.vp) { tab, pos ->
            tab.text = when (pos) {
                0 -> getString(TAB_TITLES[0])
                else -> getString(TAB_TITLES[1])
            }
        }
        mediator.attach()
    }

    companion object {

        private const val ARG_ID_TV = "idTv"
        private const val ARG_OVERVIEW = "overview"

        @JvmStatic
        fun newInstance(idTv: Int, overview: String?) =
            OverviewFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_TV, idTv)
                    putString(ARG_OVERVIEW, overview)
                }
            }
    }
}