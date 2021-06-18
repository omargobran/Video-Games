package com.dgpays.videogames.ui.adapter.holder

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.dgpays.videogames.databinding.ListItemBinding
import com.dgpays.videogames.domain.model.VideoGame
import com.dgpays.videogames.ui.callback.VideoGameCallback

class VideoGameViewHolder constructor(
    private val binding: ListItemBinding,
    private val callback: VideoGameCallback,
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    fun bind(videoGame: VideoGame) {
        ViewCompat.setTransitionName(binding.title, "title_${videoGame.id}")
        ViewCompat.setTransitionName(binding.ratingAndReleaseDate, "release_date_${videoGame.id}")
        ViewCompat.setTransitionName(binding.videoGameImage, "image_${videoGame.id}")
        binding.apply {
            this.videoGame = videoGame
            listener = this@VideoGameViewHolder
            executePendingBindings()
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            binding.videoGameImage.transitionName = "transition${videoGame.id}"
        }
    }

    override fun onClick(view: View) {
        callback.onVideoGameSelectedFromList(adapterPosition, binding);
    }
}