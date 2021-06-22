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
import com.dgpays.videogames.domain.model.VideoGame
import com.dgpays.videogames.ui.viewmodels.DetailsViewModel
import com.dgpays.videogames.ui.viewmodels.MainViewModel
import com.dgpays.videogames.util.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment(), View.OnClickListener {

    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater, container, false)

        viewModel.videoGame = DetailsFragmentArgs.fromBundle(requireArguments()).videoGame

        ViewCompat.setTransitionName(binding.title, "title_${viewModel.videoGame.id}")
        ViewCompat.setTransitionName(binding.releaseDate, "release_date_${viewModel.videoGame.id}")
        ViewCompat.setTransitionName(binding.videoGameImage, "image_${viewModel.videoGame.id}")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            desc.movementMethod = ScrollingMovementMethod()
            onClickListener = this@DetailsFragment
            videoGame = viewModel.videoGame
            executePendingBindings()
        }

        viewModel.videoGameDescriptionLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Success<VideoGame> -> {
                    progress.hideProgress()
                    viewModel.videoGame = it.data
                    viewModel.videoGame = it.data
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

        if (viewModel.videoGame.description.isEmpty()) {
            viewModel.setStateEvent(DetailsViewModel.Event.GetGameDescription(viewModel.videoGame.id))
        }
    }

    override fun onClick(view: View?) {
        if (view == binding.favoriteButton) {
            // change value of variable
            viewModel.videoGame.isFavorite = !viewModel.videoGame.isFavorite

            // update image and db
            MainViewModel.setFavorite(binding.favoriteButton, viewModel.videoGame.isFavorite)
            viewModel.setStateEvent(
                DetailsViewModel.Event.MakeGameFavorite(
                    viewModel.videoGame.id,
                    viewModel.videoGame.isFavorite
                )
            )
        }
    }
}