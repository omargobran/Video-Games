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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgpays.videogames.R
import com.dgpays.videogames.databinding.HomeFragmentBinding
import com.dgpays.videogames.databinding.ListItemBinding
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
class HomeFragment : Fragment(), VideoGameCallback {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var videoGameAdapter: VideoGameAdapter
    private lateinit var videoGamePagerAdapter: VideoGamePagerAdapter
    private lateinit var progress: Progress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        } else {
            Log.d(Constants.TAG, "onCreate: NO Animation :(")
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            progress = context as Progress
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                    + " must implement progress")
        }
    }

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

    private fun setDataToViews(data: List<VideoGame>) {
        // Top 3 items (first param inclusive, second param exclusive)
        videoGamePagerAdapter.items = if (data.size > 2) data.subList(0, 3) else data
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

    override fun onVideoGameSelected(position: Int, binding: ListItemBinding) {
        val videoGame = videoGameAdapter.items[position]

        val extras = FragmentNavigatorExtras(
            binding.title to "title_${videoGame.id}",
            binding.ratingAndReleaseDate to "release_date_${videoGame.id}",
            binding.videoGameImage to "image_${videoGame.id}")

        findNavController().navigate(HomeFragmentDirections.homeToDetails(videoGame), extras)
    }
}