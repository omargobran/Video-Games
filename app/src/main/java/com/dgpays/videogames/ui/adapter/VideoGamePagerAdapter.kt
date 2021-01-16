package com.dgpays.videogames.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dgpays.videogames.databinding.PagerItemBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.adapter.holder.VideoGamePagerViewHolder

class VideoGamePagerAdapter : RecyclerView.Adapter<VideoGamePagerViewHolder>() {
    var items: List<VideoGame> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoGamePagerViewHolder {
        return VideoGamePagerViewHolder(
            PagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoGamePagerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}