package com.dgpays.videogames.util

import android.content.Context
import android.content.SharedPreferences
import com.dgpays.videogames.R

class SharedPreferencesUtil {

    companion object {

        private fun getSharedPrefs(context: Context): SharedPreferences =
            context.getSharedPreferences(
                context.getString(R.string.pref_key),
                Context.MODE_PRIVATE
            )

        fun setCallService(context: Context, callService: Boolean) {
            getSharedPrefs(context)
                .edit()
                .putBoolean(
                    context.getString(R.string.pref_key_call_service),
                    callService
                )
                .apply()
        }

        fun isCallService(context: Context): Boolean {
            return getSharedPrefs(context).getBoolean(
                context.getString(R.string.pref_key_call_service),
                true
            )
        }

    }
}