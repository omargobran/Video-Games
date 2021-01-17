package com.dgpays.videogames.ui.adapter.holder

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.dgpays.videogames.databinding.PagerItemBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.callback.VideoGameCallback

class VideoGamePagerViewHolder constructor(
    private val binding: PagerItemBinding,
    private val callback: VideoGameCallback,
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    fun bind(videoGame: VideoGame) {
        ViewCompat.setTransitionName(binding.pagerImage, "image_${videoGame.id}")
        binding.apply {
            this.videoGame = videoGame
            listener = this@VideoGamePagerViewHolder
            executePendingBindings()
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            binding.pagerImage.transitionName = "transition${videoGame.id}"
        }
    }

    override fun onClick(view: View) {
        callback.onVideoGameSelectedFromPager(adapterPosition, binding)
    }
}