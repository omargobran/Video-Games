package com.dgpays.videogames.ui.viewmodels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dgpays.videogames.R
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.repository.Repository
import com.dgpays.videogames.util.State
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repo: Repository,
) : ViewModel() {

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, url: String) {
            url.let {
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(imageView.context)
                    .load(url)
                    .apply(requestOptions)
                    .into(imageView)
            }
        }
    }

    private val _videoGamesLiveData: MutableLiveData<State<List<VideoGame>>> = MutableLiveData()

    val videoGamesLiveData: LiveData<State<List<VideoGame>>>
        get() = _videoGamesLiveData

    private val _videoGameDescriptionLiveData: MutableLiveData<State<VideoGame>> = MutableLiveData()

    val videoGameDescriptionLiveData: LiveData<State<VideoGame>>
        get() = _videoGameDescriptionLiveData

    fun setStateEvent(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.GetGamesFromRemote -> {
                    repo.getVideoGames().onEach {
                        _videoGamesLiveData.value = it
                    }.launchIn(viewModelScope)
                }
                is Event.GetGamesFromRoom -> {
                    repo.getVideoGamesFromRoom().onEach {
                        _videoGamesLiveData.value = it
                    }.launchIn(viewModelScope)
                }
                is Event.GetGameDescription -> {
                    repo.getVideoGameById(event.id).onEach {
                        _videoGameDescriptionLiveData.value = it
                    }.launchIn(viewModelScope)
                }
                is Event.GetFavoriteGames -> {
                    repo.getFavoriteVideoGames().onEach {
                        _videoGamesLiveData.value = it
                    }.launchIn(viewModelScope)
                }
                is Event.None -> {
                    // TODO: 15/01/21 do something
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    sealed class Event(val id: Int) {
        constructor() : this(0)

        class GetGameDescription(id: Int) : Event(id)

        object GetGamesFromRemote : Event()

        object GetGamesFromRoom : Event()

        object GetFavoriteGames : Event()

        object None : Event()
    }
}