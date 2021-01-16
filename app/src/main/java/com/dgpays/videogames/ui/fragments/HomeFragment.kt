package com.dgpays.videogames.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgpays.videogames.databinding.HomeFragmentBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.adapter.VideoGameAdapter
import com.dgpays.videogames.ui.callback.VideoGameCallback
import com.dgpays.videogames.ui.viewmodels.HomeViewModel
import com.dgpays.videogames.util.Constants
import com.dgpays.videogames.util.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), VideoGameCallback {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var videoGameAdapter: VideoGameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel.videoGamesLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Success<List<VideoGame>> -> {
                    Log.d(Constants.TAG, "onCreate: ${it.data}")
                    when (viewModel.event) {
                        is HomeViewModel.Event.GetGamesFromRoom -> {
                            viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRemote)
                        }
                        else -> {
                            // do nothing
                        }
                    }
                    videoGameAdapter.addItems(it.data)
                }
                is State.Error -> {
                    Log.d(Constants.TAG, "onCreate: ${it.exception.message}")
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                is State.Loading -> {
                    Log.d(Constants.TAG, "onCreate: LOADING")
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRoom)
    }

    private fun initRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            videoGameAdapter = VideoGameAdapter(this@HomeFragment)
            adapter = videoGameAdapter
        }
    }

    override fun onVideoGameSelected(position: Int) {
        val videoGame = videoGameAdapter.items[position]
        Toast.makeText(context, "${videoGame.title} selected", Toast.LENGTH_SHORT).show()
    }
}