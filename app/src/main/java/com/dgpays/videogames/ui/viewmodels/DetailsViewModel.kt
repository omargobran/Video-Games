package com.dgpays.videogames.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.repository.Repository
import com.dgpays.videogames.util.State
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailsViewModel @ViewModelInject constructor(
    private val repo: Repository,
) : ViewModel() {

    private val _videoGameDescriptionLiveData: MutableLiveData<State<VideoGame>> = MutableLiveData()

    val videoGameDescriptionLiveData: LiveData<State<VideoGame>>
        get() = _videoGameDescriptionLiveData


    var videoGame: VideoGame? = null

    fun setStateEvent(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.GetGameDescription -> {
                    repo.getVideoGameDetails(event.id).onEach {
                        _videoGameDescriptionLiveData.value = it
                    }.launchIn(this)
                }
                is Event.MakeGameFavorite -> {
                    repo.favoriteVideoGame(event.id, event.favorite).launchIn(this)
                }
            }
        }
    }

    sealed class Event(val id: Int, val favorite: Boolean) {

        constructor(id: Int) : this(id, false)

        class GetGameDescription(id: Int) : Event(id)

        class MakeGameFavorite(id: Int, favorite: Boolean) : Event(id, favorite)

    }
}