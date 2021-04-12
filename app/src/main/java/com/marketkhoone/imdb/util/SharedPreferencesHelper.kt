package com.marketkhoone.imdb.util

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPreferencesHelper(context: Context) {


    private val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    var isFirstTimeLaunch: Boolean
        get() {
            return prefs.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        }
        set(isFirstTime) {
            prefs.edit().putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime).apply()
        }

}