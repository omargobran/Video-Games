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

class FavoritesViewModel @ViewModelInject constructor(
    private val repo: Repository,
) : ViewModel() {

    private val _videoGamesLiveData: MutableLiveData<State<List<VideoGame>>> = MutableLiveData()

    val videoGamesLiveData: LiveData<State<List<VideoGame>>>
        get() = _videoGamesLiveData

    fun setStateEvent(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.GetFavoriteGames -> {
                    repo.getFavoriteVideoGames().onEach {
                        _videoGamesLiveData.value = it
                    }.launchIn(this)
                }
            }
        }
    }

    sealed class Event {

        object GetFavoriteGames : Event()

    }
}