package com.dgpays.videogames

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.dgpays.videogames.util.SharedPreferencesUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VideoGamesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // always on startup set to true to call one time and cache in room
        SharedPreferencesUtil.setCallService(applicationContext, true)
    }
}