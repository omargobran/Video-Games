package com.dgpays.videogames.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgpays.videogames.domain.model.VideoGame
import com.dgpays.videogames.repository.Repository
import com.dgpays.videogames.util.State
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val repo: Repository,
) : ViewModel() {

    var event: Event = Event.None

    private val _videoGamesLiveData: MutableLiveData<State<List<VideoGame>>> = MutableLiveData()

    val videoGamesLiveData: LiveData<State<List<VideoGame>>>
        get() = _videoGamesLiveData

    fun setStateEvent(event: Event) {
        this.event = event
        viewModelScope.launch {
            when (event) {
                is Event.GetGamesFromRemote -> {
                    repo.getVideoGames().onEach {
                        _videoGamesLiveData.value = it
                    }.launchIn(this)
                }
                is Event.GetGamesFromRoom -> {
                    repo.getVideoGamesFromRoom().onEach {
                        _videoGamesLiveData.value = it
                    }.launchIn(this)
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    sealed class Event {

        object GetGamesFromRemote : Event()

        object GetGamesFromRoom : Event()

        object None : Event()

    }
}