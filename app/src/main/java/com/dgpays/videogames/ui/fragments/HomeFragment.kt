package com.dgpays.videogames.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dgpays.videogames.R
import com.dgpays.videogames.databinding.HomeFragmentBinding
import com.dgpays.videogames.domain.model.VideoGame
import com.dgpays.videogames.ui.viewmodels.HomeViewModel
import com.dgpays.videogames.util.Constants
import com.dgpays.videogames.util.SharedPreferencesUtil
import com.dgpays.videogames.util.State
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
                                Log.d(Constants.TAG, "onViewCreated: no video games in db, requesting from api")
                                viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRemote)
                            } else {
                                progress.hideProgress()
                                resetErrorMessageAction(true)
                                setDataToViews(it.data)
                            }
                        }
                        is HomeViewModel.Event.GetGamesFromRemote -> {
                            progress.hideProgress()
                            if (it.data.isNullOrEmpty()) {
                                errorMessageAction(getString(R.string.try_again_later), true)
                            } else {
                                resetErrorMessageAction(true)
                                setDataToViews(it.data)
                            }
                        }
                        else -> {
                            progress.hideProgress()
                            // do nothing
                        }
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

        context.let {
            if (it != null) {
                if (SharedPreferencesUtil.isCallService(it)) {
                    viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRemote)
                } else {
                    viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRoom)
                }
                SharedPreferencesUtil.setCallService(it, false)
            } else {
                viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRemote)
            }
        }
    }

    override fun getContentLayout() = binding.contentLayout

    override fun getDirectionToDetailFragment(videoGame: VideoGame) = HomeFragmentDirections.homeToDetails(videoGame)

    override fun hideViewPagerForeverInFragment() = false
}