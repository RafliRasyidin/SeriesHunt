package com.rasyidin.serieshunt.presentation.feature.detail.trailers

import android.os.Bundle
import android.view.View
import com.rasyidin.serieshunt.databinding.FragmentTrailersBinding
import com.rasyidin.serieshunt.presentation.base.BaseFragment

class TrailersFragment : BaseFragment<FragmentTrailersBinding>(FragmentTrailersBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        private const val ARG_ID_TV = "idTv"

        @JvmStatic
        fun newInstance(idTv: Int) =
            TrailersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID_TV, idTv)
                }
            }
    }
}