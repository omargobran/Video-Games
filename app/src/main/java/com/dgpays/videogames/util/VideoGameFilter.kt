package com.dgpays.videogames.util

import android.widget.Filter
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.adapter.VideoGameAdapter

class VideoGameFilter constructor(
    private var items: List<VideoGame>,
    private val adapter: VideoGameAdapter,
) : Filter() {

    override fun performFiltering(charSequence: CharSequence): FilterResults {
        val filteredItems: List<VideoGame> = items.filter {
            it.title.toLowerCase().trim().contains(charSequence)
        }

        val filterResults = FilterResults()
        filterResults.values = filteredItems
        filterResults.count = filteredItems.size

        return filterResults
    }

    override fun publishResults(charSequence: CharSequence, filterResults: FilterResults?) {
        if (filterResults != null) {
            items = filterResults.values as List<VideoGame>
            adapter.items = items
            adapter.notifyDataSetChanged()
        }
    }

}