package com.rasyidin.serieshunt.presentation.adapter.credits

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.presentation.feature.detail.overview.CreditsFragment

class CreditsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private val tvId: Int) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return CreditsFragment.newInstance(tvId, position)
    }

    companion object {

        @StringRes
        val TAB_TITLES = intArrayOf(R.string.cast, R.string.crew)
    }
}