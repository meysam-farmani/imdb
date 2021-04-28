package com.marketkhoone.imdb.view

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.di.*
import com.marketkhoone.imdb.util.SharedPreferencesHelper
import kotlinx.android.synthetic.main.activity_video.*
import javax.inject.Inject


class VideoActivity : AppCompatActivity() {

    @Inject
    @field:TypeOfContext(CONTEXT_APP)
    lateinit var prefs: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        DaggerViewModelComponent.builder()
            .appModule(AppModule(applicationContext as Application))
            .apiModule(ApiModule(applicationContext as Application))
            .build()
            .inject(this)

        var videoId = ""
        if (getIntent().hasExtra("VideoId")) {
            videoId = getIntent().getStringExtra("VideoId").toString();
        }

        you_tube_player_view.play(videoId)

        closeVideo.setOnClickListener {
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}