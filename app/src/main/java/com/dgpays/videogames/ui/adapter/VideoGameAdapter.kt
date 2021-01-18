package com.dgpays.videogames.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dgpays.videogames.databinding.ListItemBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.adapter.holder.VideoGameViewHolder
import com.dgpays.videogames.ui.callback.FilterErrorCallback
import com.dgpays.videogames.ui.callback.VideoGameCallback
import com.dgpays.videogames.util.VideoGamesFilter

class VideoGameAdapter constructor(
    private val callback: VideoGameCallback,
    private val filterCallback: FilterErrorCallback,
) : RecyclerView.Adapter<VideoGameViewHolder>(), Filterable {

    var allItems: List<VideoGame> = arrayListOf()

    var items: List<VideoGame> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoGameViewHolder {
        return VideoGameViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            callback
        )
    }

    override fun onBindViewHolder(holder: VideoGameViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getFilter(): Filter {
        return VideoGamesFilter(allItems, this, filterCallback)
    }

}