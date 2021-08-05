package com.rasyidin.serieshunt.presentation.adapter

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.presentation.feature.detail.episodes.EpisodesFragment
import com.rasyidin.serieshunt.presentation.feature.detail.overview.OverviewFragment
import com.rasyidin.serieshunt.presentation.feature.detail.trailers.TrailersFragment

class DetailPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    private val idTv: Int,
    private val overview: String?,
    private val numberOfSeason: Int
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OverviewFragment.newInstance(idTv, overview)
            1 -> EpisodesFragment.newInstance(idTv, numberOfSeason)
            else -> TrailersFragment.newInstance(idTv)
        }
    }

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.overview, R.string.episodes, R.string.videos)
    }

}