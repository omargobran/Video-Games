package com.dgpays.videogames.util

sealed class State<out OUT> {

    data class Success<out TYPE>(val data: TYPE) : State<TYPE>()

    data class Error(val exception: Exception) : State<Nothing>()

    object Loading : State<Nothing>()

}
