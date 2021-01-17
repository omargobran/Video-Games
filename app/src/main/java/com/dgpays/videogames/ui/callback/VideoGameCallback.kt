package com.dgpays.videogames.ui.callback

import com.dgpays.videogames.databinding.ListItemBinding
import com.dgpays.videogames.databinding.PagerItemBinding

interface VideoGameCallback {

    fun onVideoGameSelectedFromList(position: Int, binding: ListItemBinding)

    fun onVideoGameSelectedFromPager(position: Int, binding: PagerItemBinding)

}