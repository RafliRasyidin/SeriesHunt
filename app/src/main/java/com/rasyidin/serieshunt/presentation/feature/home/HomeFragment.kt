package com.rasyidin.serieshunt.presentation.feature.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import com.rasyidin.serieshunt.R
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.utils.onFailure
import com.rasyidin.serieshunt.core.utils.onSuccess
import com.rasyidin.serieshunt.databinding.FragmentHomeBinding
import com.rasyidin.serieshunt.presentation.adapter.OnTheAirAdapter
import com.rasyidin.serieshunt.presentation.adapter.PopularAdapter
import com.rasyidin.serieshunt.presentation.adapter.TopRatedAdapter
import com.rasyidin.serieshunt.presentation.adapter.TvShowAdapter
import com.rasyidin.serieshunt.presentation.base.BaseFragment
import com.rasyidin.serieshunt.presentation.feature.detail.DetailContentFragment.Companion.ARG_OVERVIEW
import com.rasyidin.serieshunt.presentation.feature.detail.DetailContentFragment.Companion.ARG_TV_ID
import com.rasyidin.serieshunt.presentation.utils.Constants.ANIMATION_DURATION
import com.rasyidin.serieshunt.presentation.utils.isLoading
import com.rasyidin.serieshunt.presentation.utils.isSuccess
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

    @Inject
    lateinit var bounds: ChangeBounds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation =
            TransitionInflater.from(activity).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation.setDuration(ANIMATION_DURATION)
        sharedElementReturnTransition = exitTransition()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvAiringToday()

        setupRvOnTheAir()

        setupRvPopular()

        setupRvTopRated()

        subscribeToObserver()

        navigateToDetail()

        navigateToSearchTv()
    }

    private fun exitTransition(): Transition {
        bounds.apply {
            interpolator = DecelerateInterpolator()
            duration = ANIMATION_DURATION
        }
        return bounds
    }

    private fun navigateToSearchTv() {
        binding.contentContainer.etSearch.setOnClickListener {
            val extras =
                FragmentNavigatorExtras(it to getString(R.string.searchViewTransition))
            findNavController().navigate(
                R.id.action_homeFragment_to_searchFragment,
                null,
                null,
                extras
            )
        }
    }

    private fun subscribeToObserver() {
        homeViewModel.getAiringToday()
        homeViewModel.getAiringToday.observe(viewLifecycleOwner) { result ->

            binding.shimmerRvAiringToday.isVisible = isLoading(result)

            binding.rvAiringToday.isVisible = isSuccess(result)
            result.onSuccess { resultData ->
                val isDataEmpty = resultData.data.isEmpty()
                if (!isDataEmpty) {
                    airingTodayAdapter.submitList(resultData.data)
                }
            }

            result.onFailure { throwable ->
                Log.e(HomeFragment::class.simpleName, throwable.message.toString())
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        homeViewModel.getPopular()
        homeViewModel.getPopular.observe(viewLifecycleOwner) { result ->

            binding.contentContainer.shimmerRvPopular.isVisible = isLoading(result)

            binding.contentContainer.rvPopular.isVisible = isSuccess(result)
            result.onSuccess { resultData ->
                val isDataEmpty = resultData.data.isNullOrEmpty()
                if (!isDataEmpty) {
                    popularAdapter.submitList(resultData.data)
                }
            }

            result.onFailure { throwable ->
                Log.e(HomeFragment::class.simpleName, throwable.message.toString())
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        homeViewModel.getOnTheAir()
        homeViewModel.getOnTheAir.observe(viewLifecycleOwner) { result ->

            binding.contentContainer.shimmerRvOnTheAir.isVisible = isLoading(result)

            binding.contentContainer.rvOnTheAir.isVisible = isSuccess(result)
            result.onSuccess { resultData ->
                val isDataEmpty = resultData.data.isNullOrEmpty()
                if (!isDataEmpty) {
                    onTheAirAdapter.submitList(resultData.data)
                }
            }

            result.onFailure { throwable ->
                Log.e(HomeFragment::class.simpleName, throwable.message.toString())
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        homeViewModel.getTopRated()
        homeViewModel.getTopRated.observe(viewLifecycleOwner) { result ->

            binding.contentContainer.shimmerRvTopRated.isVisible = isLoading(result)

            binding.contentContainer.rvTopRated.isVisible = isSuccess(result)
            result.onSuccess { resultData ->
                val isDataEmpty = resultData.data.isNullOrEmpty()
                if (!isDataEmpty) {
                    topRatedAdapter.submitList(resultData.data)
                }
            }

            result.onFailure { throwable ->
                Log.e(HomeFragment::class.simpleName, throwable.message.toString())
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupTransitionName(imagePoster: View?, tvTitle: View?, tvShow: TvShow) {
        if (tvTitle == null) {
            ViewCompat.setTransitionName(
                imagePoster!!,
                getString(R.string.imageTransition).plus(tvShow.id)
            )
        } else {
            ViewCompat.setTransitionName(
                imagePoster!!,
                getString(R.string.imageTransition).plus(tvShow.id)
            )
            ViewCompat.setTransitionName(
                tvTitle,
                getString(R.string.TitleTransition).plus(tvShow.id)
            )
        }
    }

    private fun navigateToDetail() {
        var args: Bundle
        popularAdapter.onItemClick = { tvShow, imgPoster, _ ->
            setupTransitionName(imgPoster, null, tvShow)
            val extras = FragmentNavigatorExtras(
                imgPoster!! to getString(R.string.imageTransition).plus(tvShow.id)
            )
            args = Bundle().apply {
                putInt(ARG_TV_ID, tvShow.id)
                putString(ARG_OVERVIEW, tvShow.overview)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailContentFragment,
                args,
                null,
                extras
            )
        }

        topRatedAdapter.onItemClick = { tvShow, imgPoster, _ ->
            setupTransitionName(imgPoster, null, tvShow)
            val extras = FragmentNavigatorExtras(
                imgPoster!! to getString(R.string.imageTransition).plus(tvShow.id)
            )
            args = Bundle().apply {
                putInt(ARG_TV_ID, tvShow.id)
                putString(ARG_OVERVIEW, tvShow.overview)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailContentFragment,
                args,
                null,
                extras
            )
        }

        onTheAirAdapter.onItemClick = { tvShow, imgPoster, _ ->
            setupTransitionName(imgPoster, null, tvShow)
            val extras = FragmentNavigatorExtras(
                imgPoster!! to getString(R.string.imageTransition).plus(tvShow.id)
            )
            args = Bundle().apply {
                putInt(ARG_TV_ID, tvShow.id)
                putString(ARG_OVERVIEW, tvShow.overview)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailContentFragment,
                args,
                null,
                extras
            )
        }

        airingTodayAdapter.onItemClick = { tvShow, imgPoster, tvTitle ->
            setupTransitionName(imgPoster, tvTitle, tvShow)
            val extras = FragmentNavigatorExtras(
                imgPoster!! to getString(R.string.imageTransition).plus(tvShow.id),
                tvTitle!! to getString(R.string.TitleTransition).plus(tvShow.id)
            )
            args = Bundle().apply {
                putInt(ARG_TV_ID, tvShow.id)
                putString(ARG_OVERVIEW, tvShow.overview)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailContentFragment,
                args,
                null,
                extras
            )
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