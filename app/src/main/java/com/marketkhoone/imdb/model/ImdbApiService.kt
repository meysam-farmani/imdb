package com.marketkhoone.imdb.model

import MovieData
import com.marketkhoone.imdb.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class ImdbApiService {
    @Inject
    lateinit var api: ImdbApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getTop250Movies(apiKey: String?): Single<MovieData> {
        return api.getTop250Movies(apiKey)
    }
}