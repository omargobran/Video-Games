package com.dgpays.videogames.ui.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dgpays.videogames.databinding.DetailsFragmentBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.viewmodels.DetailsViewModel
import com.dgpays.videogames.ui.viewmodels.MainViewModel
import com.dgpays.videogames.util.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment(), View.OnClickListener {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: DetailsFragmentBinding
    private lateinit var game: VideoGame

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)

        game = DetailsFragmentArgs.fromBundle(requireArguments()).videoGame

        ViewCompat.setTransitionName(binding.title, "title_${game.id}")
        ViewCompat.setTransitionName(binding.releaseDate, "release_date_${game.id}")
        ViewCompat.setTransitionName(binding.videoGameImage, "image_${game.id}")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            desc.movementMethod = ScrollingMovementMethod()
            onClickListener = this@DetailsFragment
            videoGame = game
            executePendingBindings()
        }

        viewModel.videoGameDescriptionLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Success<VideoGame> -> {
                    progress.hideProgress()
                    binding.videoGame = it.data
                }
                is State.Error -> {
                    progress.hideProgress()
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                is State.Loading -> {
                    progress.showProgress(null)
                }
            }
        })

        if (game.description.isNullOrEmpty()) {
            viewModel.setStateEvent(DetailsViewModel.Event.GetGameDescription(game.id))
        }
    }

    override fun onClick(view: View?) {
        if (view == binding.favoriteButton) {
            // change value of variable
            game.isFavorite = !game.isFavorite

            // update image and db
            MainViewModel.setFavorite(binding.favoriteButton, game.isFavorite)
            viewModel.setStateEvent(
                DetailsViewModel.Event.MakeGameFavorite(
                    game.id,
                    game.isFavorite
                )
            )
        }
    }
}