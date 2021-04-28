package com.marketkhoone.imdb.model

import android.app.Application
import com.marketkhoone.imdb.di.ApiModule
import com.marketkhoone.imdb.di.AppModule
import com.marketkhoone.imdb.di.DaggerApiComponent
import com.marketkhoone.imdb.model.entity.*
import io.reactivex.Single
import okhttp3.Response
import javax.inject.Inject

class ImdbApiService(application: Application) {
    @Inject
    lateinit var api: ImdbApi

    init {
//        DaggerApiComponent.create().inject(this)
        DaggerApiComponent.builder()
            .apiModule(ApiModule(application))
            .build()
            .inject(this)
    }

    fun getInTheaters(apiKey: String): Single<NewMovie> {
        return api.getInTheaters(apiKey)
    }

    fun getComingSoon(apiKey: String): Single<NewMovie> {
        return api.getComingSoon(apiKey)
    }

    fun getYouTubeTrailer(apiKey: String, imdbId: String?): Single<YouTubeTrailer> {
        return api.getYouTubeTrailer(apiKey, imdbId)
    }

    fun getMovieTitle(apiKey: String, imdbId: String?): Single<MovieTitle> {
        return api.getMovieTitle(apiKey, imdbId)
    }

    fun getTop250Movies(apiKey: String): Single<TopMovie> {
        return api.getTop250Movies(apiKey)
    }

    fun getTop250TVs(apiKey: String): Single<TopMovie> {
        return api.getTop250TVs(apiKey)
    }

    fun getMostPopularMovies(apiKey: String): Single<PopularMovie> {
        return api.getMostPopularMovies(apiKey)
    }

    fun getMostPopularTVs(apiKey: String): Single<PopularMovie> {
        return api.getMostPopularTVs(apiKey)
    }

    fun getBoxOffice(apiKey: String): Single<BoxOfficeMovie> {
        return api.getBoxOffice(apiKey)
    }

    fun getSearchTitle(apiKey: String, expression: String?): Single<SearchTitle> {
        return api.getSearchTitle(apiKey, expression)
    }
}