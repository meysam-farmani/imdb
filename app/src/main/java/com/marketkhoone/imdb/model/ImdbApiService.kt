package com.marketkhoone.imdb.model

import com.marketkhoone.imdb.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class ImdbApiService {
    @Inject
    lateinit var api: ImdbApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getTop250Movies(apiKey: String): Single<NewMovie> {
        return api.getTop250Movies(apiKey)
    }

    fun getInTheaters(apiKey: String): Single<NewMovie> {
        return api.getInTheaters(apiKey)
    }

    fun getComingSoon(apiKey: String): Single<NewMovie> {
        return api.getComingSoon(apiKey)
    }

    fun getFullCast(apiKey: String, imdbId: String?): Single<FullCast> {
        return api.getFullCast(apiKey, imdbId)
    }

    fun getYouTubeTrailer(apiKey: String, imdbId: String?): Single<YouTubeTrailer> {
        return api.getYouTubeTrailer(apiKey, imdbId)
    }

    fun getTrailer(apiKey: String, imdbId: String?): Single<ImdbTrailer> {
        return api.getTrailer(apiKey, imdbId)
    }
}