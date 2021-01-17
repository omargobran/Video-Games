package com.dgpays.videogames.ui.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
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
import com.dgpays.videogames.R
import com.dgpays.videogames.databinding.ContentLayoutBinding
import com.dgpays.videogames.databinding.HomeFragmentBinding
import com.dgpays.videogames.databinding.ListItemBinding
import com.dgpays.videogames.databinding.PagerItemBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.adapter.VideoGameAdapter
import com.dgpays.videogames.ui.adapter.VideoGamePagerAdapter
import com.dgpays.videogames.ui.callback.VideoGameCallback
import com.dgpays.videogames.ui.viewmodels.HomeViewModel
import com.dgpays.videogames.util.Constants
import com.dgpays.videogames.util.Progress
import com.dgpays.videogames.util.State
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseContentLayoutFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.videoGamesLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Success<List<VideoGame>> -> {
                    when (viewModel.event) {
                        is HomeViewModel.Event.GetGamesFromRoom -> {
                            if (it.data.isNullOrEmpty()) {
                                viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRemote)
                            } else {
                                progress.hideProgress()
                                setDataToViews(it.data)
                            }
                        }
                        is HomeViewModel.Event.GetGamesFromRemote -> {
                            progress.hideProgress()
                            setDataToViews(it.data)
                        }
                        else -> {
                            progress.hideProgress()
                            // do nothing
                        }
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

        viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRoom)
    }

    override fun getContentLayout(): ContentLayoutBinding {
        return binding.contentLayout
    }

    override fun getDirectionToDetailFragment(videoGame: VideoGame): NavDirections {
        return HomeFragmentDirections.homeToDetails(videoGame)
    }
}