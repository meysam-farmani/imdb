package com.marketkhoone.imdb.util

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPreferencesHelper(context: Context) {

    private val API_KEY = "k_idj95j2b"
    private val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    var isFirstTimeLaunch: Boolean
        get() {
            return prefs.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        }
        set(isFirstTime) {
            prefs.edit().putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime).apply()
        }

    fun getApiKey() = API_KEY

}