package com.marketkhoone.imdb.model

import MovieData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ImdbApi {

    @GET("Top250Movies/{apiKey}")
    fun getTop250Movies(
        @Path("apiKey") apiKey: String?): Single<MovieData>
}