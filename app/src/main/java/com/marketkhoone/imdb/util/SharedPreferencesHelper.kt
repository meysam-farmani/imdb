package com.marketkhoone.imdb.util

import android.content.Context
import android.content.res.Resources
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.marketkhoone.imdb.R
import java.util.*

class SharedPreferencesHelper(context: Context) {

    private val PREFS_API_KEY = "Api Key"
    private val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    private var mContext = context

    var isFirstTimeLaunch: Boolean
        get() {
            return prefs.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        }
        set(isFirstTime) {
            prefs.edit().putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime).apply()
        }

    fun getApiKey(): String {
        var apiKey = ""

        var apiKeys = ArrayList(Arrays.asList(*mContext.resources.getStringArray(R.array.apiKeys)))

        var latestApiKey = prefs.getString(PREFS_API_KEY, "")

        if(latestApiKey == ""){
            apiKey = apiKeys.get(0).toString()
        }else{
            apiKeys.forEachIndexed { index, s ->
                if(s == latestApiKey){
                    if(index == apiKeys.size - 1){
                        apiKey = apiKeys.get(0).toString()
                    }else{
                        apiKey = apiKeys.get(index + 1).toString()
                    }
                }
            }
        }

        prefs?.edit(commit = true) {
            putString(PREFS_API_KEY, apiKey)
        }

        return apiKey
    }

}