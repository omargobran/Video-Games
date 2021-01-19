package com.dgpays.videogames.ui.viewmodels

import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dgpays.videogames.R
import com.dgpays.videogames.repository.Repository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repo: Repository,
) : ViewModel() {

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, url: String) {
            val circularProgressDrawable = CircularProgressDrawable(imageView.context).apply {
                strokeWidth = 8f
                centerRadius = 30f
                setColorFilter(R.attr.colorPrimaryVariant, PorterDuff.Mode.MULTIPLY)
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
            imageView.setImageResource(
                if (isFavorite) {
                    R.drawable.ic_like
                } else {
                    R.drawable.ic_like_outline
                }
            )
        }

    }

    fun deleteAllVideoGames() {
        viewModelScope.launch {
            repo.deleteAllVideoGames().launchIn(this)
        }
    }
}