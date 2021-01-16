package com.dgpays.videogames.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.dgpays.videogames.R
import com.dgpays.videogames.databinding.ActivityMainBinding
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.ui.viewmodels.MainViewModel
import com.dgpays.videogames.util.Constants
import com.dgpays.videogames.util.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragment_id)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.favoritesFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)

//        viewModel.videoGameDescriptionLiveData.observe(this, Observer {
//            when (it) {
//                is State.Success<VideoGame> -> {
//                    Log.d(Constants.TAG, "onCreate: ${it.data}")
//                }
//                is State.Error -> {
//                    Log.d(Constants.TAG, "onCreate: ${it.exception.message}")
//                }
//                is State.Loading -> {
//                    Log.d(Constants.TAG, "onCreate: LOADING")
//                }
//            }
//        })
//

//
//        viewModel.setStateEvent(MainViewModel.Event.GetGamesFromRoom)
//        viewModel.setStateEvent(MainViewModel.Event.GetGamesFromRemote)
    }
}