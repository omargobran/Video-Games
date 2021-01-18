package com.dgpays.videogames.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import com.dgpays.videogames.R
import com.dgpays.videogames.databinding.ContentLayoutBinding
import com.dgpays.videogames.databinding.FavoritesFragmentBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.viewmodels.FavoritesViewModel
import com.dgpays.videogames.util.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseContentLayoutFragment() {

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var binding: FavoritesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.videoGamesLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Success<List<VideoGame>> -> {
                    if (it.data.isNullOrEmpty()) {
                        progress.hideProgress()
                        errorMessageAction(getString(R.string.no_favorites), true)
                    } else {
                        progress.hideProgress()
                        resetErrorMessageAction(true)
                        setDataToViews(it.data)
                    }
                }
                is State.Error -> {
                    progress.hideProgress()
                    errorMessageAction(getString(R.string.try_again_later), true)
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                is State.Loading -> {
                    progress.showProgress(null)
                }
            }
        })

        viewModel.setStateEvent(FavoritesViewModel.Event.GetFavoriteGames)
    }

    override fun getContentLayout() = binding.contentLayout

    override fun getDirectionToDetailFragment(videoGame: VideoGame) = FavoritesFragmentDirections.favoritesToDetails(videoGame)

    override fun hideViewPagerForeverInFragment() = true
}