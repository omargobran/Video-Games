package com.dgpays.videogames.ui.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgpays.videogames.databinding.ContentLayoutBinding
import com.dgpays.videogames.databinding.FavoritesFragmentBinding
import com.dgpays.videogames.databinding.ListItemBinding
import com.dgpays.videogames.databinding.PagerItemBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.adapter.VideoGameAdapter
import com.dgpays.videogames.ui.adapter.VideoGamePagerAdapter
import com.dgpays.videogames.ui.callback.VideoGameCallback
import com.dgpays.videogames.ui.viewmodels.FavoritesViewModel
import com.dgpays.videogames.util.Constants
import com.dgpays.videogames.util.Progress
import com.dgpays.videogames.util.State
import com.google.android.material.tabs.TabLayoutMediator
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
                        // TODO: 17/01/21 Henuz Favori secilmedi
                    } else {
                        progress.hideProgress()
                        setDataToViews(it.data)
                    }
                }
                is State.Error -> {
                    progress.hideProgress()
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                is State.Loading -> {
                    progress.showProgress(null)
                }
            }
        })

        viewModel.setStateEvent(FavoritesViewModel.Event.GetFavoriteGames)
    }

    override fun getContentLayout(): ContentLayoutBinding {
        return binding.contentLayout
    }

    override fun getDirectionToDetailFragment(videoGame: VideoGame): NavDirections {
        return FavoritesFragmentDirections.favoritesToDetails(videoGame)
    }
}