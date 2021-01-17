package com.dgpays.videogames.ui.callback

import com.dgpays.videogames.databinding.ListItemBinding

interface VideoGameCallback {

    fun onVideoGameSelected(position: Int, binding: ListItemBinding)

}