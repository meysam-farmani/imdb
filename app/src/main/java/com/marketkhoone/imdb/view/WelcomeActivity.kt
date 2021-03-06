package com.marketkhoone.imdb.view

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.di.*
import com.marketkhoone.imdb.util.SharedPreferencesHelper
import javax.inject.Inject


class WelcomeActivity : AppCompatActivity() {

    @Inject
    @field:TypeOfContext(CONTEXT_APP)
    lateinit var prefs: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        DaggerViewModelComponent.builder()
            .appModule(AppModule(applicationContext as Application))
            .apiModule(ApiModule(applicationContext as Application))
            .build()
            .inject(this)

//        prefs.isFirstTimeLaunch = true
        if(!prefs.isFirstTimeLaunch){
            navigateToMainActivity()
        }
    }

    fun navigateToMainActivity() {
        val mainActivity = Intent(this, MainActivity::class.java)
        startActivity(mainActivity)
        finish()
    }
}