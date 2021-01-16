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
import com.dgpays.videogames.ui.adapter.VideoGamePagerAdapter
import com.dgpays.videogames.ui.callback.VideoGameCallback
import com.dgpays.videogames.ui.viewmodels.HomeViewModel
import com.dgpays.videogames.util.Constants
import com.dgpays.videogames.util.State
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), VideoGameCallback {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var videoGameAdapter: VideoGameAdapter
    private lateinit var videoGamePagerAdapter: VideoGamePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initViewPagerTabLayout()
        initRecyclerView()

        viewModel.videoGamesLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Success<List<VideoGame>> -> {
                    when (viewModel.event) {
                        is HomeViewModel.Event.GetGamesFromRoom -> {
                            if (it.data.isNullOrEmpty()) {
                                viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRemote)
                            } else {
                                setDataToViews(it.data)
                            }
                        }
                        is HomeViewModel.Event.GetGamesFromRemote -> {
                            setDataToViews(it.data)
                        }
                        else -> {
                            // do nothing
                        }
                    }
                }
                is State.Error -> {
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                is State.Loading -> {
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.setStateEvent(HomeViewModel.Event.GetGamesFromRoom)
    }

    private fun setDataToViews(data: List<VideoGame>) {
        // Top 3 items (first param inclusive, second param exclusive)
        videoGamePagerAdapter.items = data.subList(0, 3)
        videoGameAdapter.items = data
    }

    private fun initViewPager() {
        binding.viewPager.apply {
            videoGamePagerAdapter = VideoGamePagerAdapter()
            adapter = videoGamePagerAdapter
        }
    }

    private fun initViewPagerTabLayout() {
        val emptyStrategy = TabLayoutMediator.TabConfigurationStrategy { _, _ -> }
        TabLayoutMediator(binding.viewPagerTabLayout, binding.viewPager, emptyStrategy).attach()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
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