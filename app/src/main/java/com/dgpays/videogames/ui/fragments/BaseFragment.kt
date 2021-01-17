package com.dgpays.videogames.ui.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import com.dgpays.videogames.util.Constants
import com.dgpays.videogames.util.Progress

abstract class BaseFragment: Fragment() {
    lateinit var progress: Progress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        } else {
            Log.d(Constants.TAG, "onCreate: NO Animation :(")
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            progress = context as Progress
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                    + " must implement progress")
        }
    }
}