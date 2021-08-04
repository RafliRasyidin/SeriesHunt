package com.rasyidin.serieshunt.presentation.feature.detail.episodes

import android.os.Bundle
import android.view.View
import com.rasyidin.serieshunt.databinding.FragmentEpisodesBinding
import com.rasyidin.serieshunt.presentation.base.BaseFragment

class EpisodesFragment : BaseFragment<FragmentEpisodesBinding>(FragmentEpisodesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        private const val ARG_ID_TV = "idTv"
        @JvmStatic
        fun newInstance(idTv: Int) =
            EpisodesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_TV, idTv)
                }
            }
    }
}