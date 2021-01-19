package com.dgpays.videogames.util

import android.widget.Filter
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.adapter.VideoGameAdapter
import com.dgpays.videogames.ui.callback.FilterErrorCallback

class VideoGamesFilter constructor(
    private var originalItems: List<VideoGame>,
    private val adapter: VideoGameAdapter,
    private val callback: FilterErrorCallback,
) : Filter() {

    override fun performFiltering(charSequence: CharSequence): FilterResults {
        val filteredItems: List<VideoGame> = originalItems.filter {
            it.title.toLowerCase().trim().contains(charSequence)
        }

        return FilterResults().apply {
            values = filteredItems
            count = filteredItems.size
        }
    }

    override fun publishResults(charSequence: CharSequence, filterResults: FilterResults?) {
        if (filterResults != null) {
            val filteredItems = filterResults.values as List<VideoGame>
            if (filteredItems.isNullOrEmpty()) {
                filterErrorAction()
            } else {
                adapter.items = filteredItems
            }
        } else {
            filterErrorAction()
        }
    }

    private fun filterErrorAction() {
        adapter.items = ArrayList()
        callback.onFilterError()
    }

}