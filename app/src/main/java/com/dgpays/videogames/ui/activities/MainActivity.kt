package com.dgpays.videogames.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dgpays.videogames.R
import com.dgpays.videogames.databinding.ActivityMainBinding
import com.dgpays.videogames.ui.viewmodels.MainViewModel
import com.dgpays.videogames.util.Progress
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Progress {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragment_id)

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.favoritesFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun showProgress(message: String?) {
        if (!message.isNullOrEmpty()) {
            binding.progressMessage = message
        }
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressVisibility = View.GONE
    }
}