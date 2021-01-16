package com.dgpays.videogames.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dgpays.videogames.databinding.ListItemBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.callback.VideoGameCallback

class VideoGameViewHolder constructor(
    private val binding: ListItemBinding,
    private val callback: VideoGameCallback
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    fun bind(videoGame: VideoGame) {
        binding.listener = this
        binding.videoGame = videoGame
    }

    override fun onClick(view: View) {
        callback.onVideoGameSelected(adapterPosition);
    }
}