package com.dgpays.videogames.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dgpays.videogames.databinding.ListItemBinding
import com.dgpays.videogames.databinding.PagerItemBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.callback.VideoGameCallback

class VideoGamePagerViewHolder constructor(
    private val binding: PagerItemBinding
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    fun bind(videoGame: VideoGame) {
        binding.videoGame = videoGame
    }

    override fun onClick(view: View) {
        // TODO: 16/01/21 Implement onClick
    }
}