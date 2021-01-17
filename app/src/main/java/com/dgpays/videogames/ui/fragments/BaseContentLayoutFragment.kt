package com.dgpays.videogames.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dgpays.videogames.databinding.ContentLayoutBinding
import com.dgpays.videogames.databinding.ListItemBinding
import com.dgpays.videogames.databinding.PagerItemBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.adapter.VideoGameAdapter
import com.dgpays.videogames.ui.adapter.VideoGamePagerAdapter
import com.dgpays.videogames.ui.callback.VideoGameCallback

abstract class BaseContentLayoutFragment :
    BaseFragment(),
    VideoGameCallback,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var videoGameAdapter: VideoGameAdapter
    private lateinit var videoGamePagerAdapter: VideoGamePagerAdapter

    private lateinit var binding: ContentLayoutBinding

    abstract fun getContentLayout(): ContentLayoutBinding
    abstract fun getDirectionToDetailFragment(videoGame: VideoGame): NavDirections

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getContentLayout()
        initViewPager()
        initViewPagerDotsIndicator()
        initRecyclerView()
        initSearchView()
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(this)
    }

    fun setDataToViews(data: List<VideoGame>) {
        val top3VideoGames = data.dropLastWhile { data.indexOf(it) > 2 }
        val restOfGames = data.drop(3)

        videoGamePagerAdapter.items = top3VideoGames
        videoGameAdapter.items = restOfGames
    }

    private fun initViewPager() {
        binding.viewPager.apply {
            videoGamePagerAdapter = VideoGamePagerAdapter(this@BaseContentLayoutFragment)
            adapter = videoGamePagerAdapter
        }
    }

    private fun initViewPagerDotsIndicator() {
        binding.dotsIndicator.setViewPager2(binding.viewPager)
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            videoGameAdapter = VideoGameAdapter(this@BaseContentLayoutFragment)
            adapter = videoGameAdapter
        }
    }

    override fun onVideoGameSelectedFromPager(position: Int, binding: PagerItemBinding) {
        val videoGame = videoGamePagerAdapter.items[position]

        selectedVideoGameProcess(videoGame, binding)
    }

    override fun onVideoGameSelectedFromList(position: Int, binding: ListItemBinding) {
        val videoGame = videoGameAdapter.items[position]

        selectedVideoGameProcess(videoGame, binding)
    }

    private fun selectedVideoGameProcess(videoGame: VideoGame, binding: ViewDataBinding) {

        var extras: FragmentNavigator.Extras = FragmentNavigatorExtras()

        when (binding) {
            is ListItemBinding -> {
                extras = FragmentNavigatorExtras(
                    binding.title to "title_${videoGame.id}",
                    binding.ratingAndReleaseDate to "release_date_${videoGame.id}",
                    binding.videoGameImage to "image_${videoGame.id}")
            }
            is PagerItemBinding -> {
                extras = FragmentNavigatorExtras(
                    binding.pagerImage to "image_${videoGame.id}"
                )
            }
        }

        findNavController().navigate(
            getDirectionToDetailFragment(videoGame),
            extras
        )
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            if (newText.length >= 3) {
                videoGameAdapter.filter.filter(newText)
                return true
            }
        }
        return false
    }
}