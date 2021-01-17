package com.dgpays.videogames.ui.viewmodels

import android.graphics.Color
import android.graphics.ColorFilter
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dgpays.videogames.R
import com.dgpays.videogames.repository.Repository

class MainViewModel @ViewModelInject constructor(
    private val repo: Repository,
) : ViewModel() {

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, url: String) {
            val circularProgressDrawable = CircularProgressDrawable(imageView.context).apply {
                strokeWidth = 7f
                centerRadius = 30f
                setColorSchemeColors(R.style.Theme_VideoGames)
                start()
            }

            val requestOptions = RequestOptions()
                .placeholder(circularProgressDrawable)
                .error(R.drawable.ic_broken_image)

            Glide.with(imageView.context)
                .load(url)
                .apply(requestOptions)
                .into(imageView)
        }

        @BindingAdapter("favoriteImage")
        @JvmStatic
        fun setFavorite(imageView: ImageView, isFavorite: Boolean) {
            imageView.apply {
                setImageResource(
                    if (isFavorite) {
                        R.drawable.ic_star
                    } else {
                        R.drawable.ic_outline_star
                    }
                )
                setColorFilter(R.style.Theme_VideoGames)
            }
        }

    }

}